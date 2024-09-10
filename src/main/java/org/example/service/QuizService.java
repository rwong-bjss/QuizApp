package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.mapper.UserMapper;
import org.example.model.UserEntity;
import org.example.models.InitialUser;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Transactional
    public UserDto createUser(InitialUser user) {
        UserEntity userDto = userMapper.createUserDto(user.getName());
        UserEntity save = userRepository.save(userDto);
        return userMapper.userEntityToUserDto(save);
    }

//    @Transactional
//    public User updateUserScore(String session, boolean isCorrect, Long questionId) {
//        User user = userRepository.findBySession(session);
//        if (user == null) {
//            throw new RuntimeException("User not found");
//        }
//
//        user.setQuestionsAnswered(user.getQuestionsAnswered() + 1);
//        if (isCorrect) {
//            user.setCorrectAnswers(user.getCorrectAnswers() + 1);
//        }
//        user.getAnsweredQuestions().add(questionId);
//
//        return userRepository.save(user);
//    }

    public UserDto getUserBySession(String session) {
        UserEntity bySession = userRepository.findBySession(session);
        return  userMapper.userEntityToUserDto(bySession);
    }
}