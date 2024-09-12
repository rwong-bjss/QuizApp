package org.example.controller;

import org.example.dto.UserDto;
import org.example.model.Answer;
import org.example.model.InitialUser;
import org.example.model.Question;
import org.example.model.QuestionAnswer;
import org.example.service.UserService;
import org.example.service.TriviaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final UserService userService;
    private final TriviaService triviaService;

    @Autowired
    public QuizController(UserService userService, TriviaService triviaService) {
        this.userService = userService;
        this.triviaService = triviaService;
    }

    @PostMapping("/start")
    public ResponseEntity<UserDto> startQuiz(@RequestBody InitialUser initialUser) {
        return ResponseEntity.ok(userService.createUser(initialUser));
    }

    @GetMapping("/question")
    public ResponseEntity<Question> getQuestion(@RequestHeader("sessionId") UUID sessionId) {
        Question question = triviaService.getNextQuestion(sessionId);
        return ResponseEntity.ok(question);
    }

    @PostMapping("/answer")
    public ResponseEntity<QuestionAnswer> answerQuestion(@RequestHeader("sessionId") UUID sessionId,
                                                         @RequestBody Answer answer) {
        boolean correct = triviaService.markQuestionAsAnswered(sessionId, answer.getQuestionId(), answer.getAnswer());
        userService.updateUserScore(String.valueOf(sessionId), correct, (long) answer.getQuestionId());
        QuestionAnswer qa = new QuestionAnswer(answer.getQuestionId(), correct);
        return ResponseEntity.ok(qa);
    }

    @GetMapping("/results")
    public ResponseEntity<UserDto> getQuizResults(@RequestHeader("sessionId") UUID sessionId) {
        return ResponseEntity.ok(userService.getUserBySession(String.valueOf(sessionId)));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception e) {
        Error error = new Error(e.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
