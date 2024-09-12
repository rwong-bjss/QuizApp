``` mermaid
sequenceDiagram
    participant Client
    participant Main as Main Controller
    participant TriviaService
    participant QuestionMapper
    participant RestTemplate
    participant StoredQuestionRepository
    participant Database as PostgreSQL Database
    participant ExternalAPI as External Trivia API

    Client->>Main: POST /start-quiz
    Main->>TriviaService: fetchAndStoreQuestions()
    TriviaService->>RestTemplate: getForObject(apiUrl, TriviaResponse.class)
    RestTemplate->>ExternalAPI: HTTP GET Request
    ExternalAPI-->>RestTemplate: TriviaResponse
    RestTemplate-->>TriviaService: TriviaResponse
    TriviaService->>QuestionMapper: triviaQuestionToStoredQuestion()
    QuestionMapper-->>TriviaService: List<StoredQuestion>
    TriviaService->>StoredQuestionRepository: saveAll(storedQuestions)
    StoredQuestionRepository->>Database: Save questions
    Database-->>StoredQuestionRepository: Confirmation
    StoredQuestionRepository-->>TriviaService: Confirmation
    TriviaService-->>Main: Completion
    Main-->>Client: "Quiz started! Questions fetched and stored."

    Client->>Main: GET /question
    Main->>TriviaService: getNextQuestion()
    TriviaService->>StoredQuestionRepository: findFirstByAnsweredFalse()
    StoredQuestionRepository->>Database: Query
    Database-->>StoredQuestionRepository: StoredQuestion
    StoredQuestionRepository-->>TriviaService: StoredQuestion
    TriviaService->>QuestionMapper: storedQuestionToQuestion()
    QuestionMapper-->>TriviaService: Question
    TriviaService-->>Main: Question
    Main-->>Client: Question

    Client->>Main: POST /answer/{questionId}
    Main->>TriviaService: markQuestionAsAnswered(questionId)
    TriviaService->>StoredQuestionRepository: findById(questionId)
    StoredQuestionRepository->>Database: Query
    Database-->>StoredQuestionRepository: StoredQuestion
    StoredQuestionRepository-->>TriviaService: StoredQuestion
    TriviaService->>StoredQuestionRepository: save(updatedQuestion)
    StoredQuestionRepository->>Database: Update
    Database-->>StoredQuestionRepository: Confirmation
    StoredQuestionRepository-->>TriviaService: Confirmation
    TriviaService-->>Main: Completion
    Main-->>Client: "Answer recorded. Get the next question."```