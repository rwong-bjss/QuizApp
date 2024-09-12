package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.StoredQuestion;
import org.example.entity.UserAnswered;
import org.example.model.Question;
import org.example.model.TriviaResponse;
import org.example.repository.StoredQuestionRepository;
import org.example.repository.UserAnsweredRepository;
import org.example.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TriviaService {

    @Value("${trivia.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private final StoredQuestionRepository questionRepository;
    private final UserAnsweredRepository userAnsweredRepository;
    private final QuestionMapper questionMapper;

    @Transactional
    public void fetchAndStoreQuestions() {
        TriviaResponse response = restTemplate.getForObject(apiUrl, TriviaResponse.class);
        List<StoredQuestion> storedQuestions = response.getResults().stream()
                .map(questionMapper::triviaQuestionToStoredQuestion)
                .collect(Collectors.toList());
        questionRepository.saveAll(storedQuestions);
    }

    @Transactional(readOnly = true)
    public Question getNextQuestion(UUID sessionId) {
        StoredQuestion storedQuestion = questionRepository.findAll().stream()
                .filter(q -> !userAnsweredRepository.existsBySessionIdAndQuestionId(sessionId, q.getId()))
                .findFirst()
                .orElse(null);
        return storedQuestion != null ? questionMapper.storedQuestionToQuestion(storedQuestion) : null;
    }

    @Transactional
    public boolean markQuestionAsAnswered(UUID sessionId, int questionId, String userAnswer) {
        StoredQuestion storedQuestion = questionRepository.findById((long) questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        
        boolean correct = storedQuestion.getCorrectAnswer().equals(userAnswer);
        
        UserAnswered userAnswered = new UserAnswered();
        userAnswered.setSessionId(sessionId);
        userAnswered.setQuestion(storedQuestion);
        userAnswered.setCorrect(correct);
        userAnsweredRepository.save(userAnswered);
        
        return correct;
    }
}