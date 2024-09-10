package org.example.controller;

import org.example.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

///todo DELETE ME

//@RestController
//@RequestMapping("/api/quiz")
//public class QuizController {
//
//    private final QuizService quizService;
//
//    @Autowired
//    public QuizController(QuizService quizService) {
//        this.quizService = quizService;
//    }
//
//    @PostMapping("/users")
//    public ResponseEntity<User> createUser(@RequestParam String name) {
//        User user = quizService.createUser(name);
//        return ResponseEntity.ok(user);
//    }
//
//    @PutMapping("/users/{session}/answer")
//    public ResponseEntity<User> updateUserScore(
//            @PathVariable String session,
//            @RequestParam boolean isCorrect,
//            @RequestParam Long questionId) {
//        User user = quizService.updateUserScore(session, isCorrect, questionId);
//        return ResponseEntity.ok(user);
//    }
//
//    @GetMapping("/users/{session}")
//    public ResponseEntity<User> getUser(@PathVariable String session) {
//        User user = quizService.getUserBySession(session);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(user);
//    }
//}