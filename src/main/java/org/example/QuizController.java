package org.example;

import org.example.dto.UserDto;
import org.example.models.Answer;
import org.example.models.InitialUser;
import org.example.models.Question;
import org.example.models.QuestionAnswer;
import org.example.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/start")
    public ResponseEntity<UserDto> startQuiz(@RequestBody InitialUser initialUser) {
        return ResponseEntity.ok(quizService.createUser(initialUser));
    }

//    @GetMapping("/question")
//    public ResponseEntity<Question> getQuestion(@RequestHeader("sessionId") UUID sessionId) {
//        // TODO: Implement logic to get the next question
//        Question question = new Question();
//        // Populate question details
//        return ResponseEntity.ok(question);
//    }

//    @PostMapping("/answer")
//    public ResponseEntity<QuestionAnswer> submitAnswer(
//        @RequestHeader("sessionId") UUID sessionId,
//        @RequestBody Answer answer) {
//        // TODO: Implement answer submission logic
//        QuestionAnswer questionAnswer = new QuestionAnswer();
//        questionAnswer.setQuestionId(answer.getQuestionId());
//        // Set correct answer and evaluate
//        return ResponseEntity.ok(questionAnswer);
//    }

    @GetMapping("/results")
    public ResponseEntity<UserDto> getQuizResults(@RequestHeader("sessionId") UUID sessionId) {
        // TODO: Implement logic to get final quiz results
        return ResponseEntity.ok(quizService.getUserBySession(String.valueOf(sessionId)));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception e) {
        Error error = new Error(e.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
