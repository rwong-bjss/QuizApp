package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.mapper.UserMapper;
import org.example.model.UserEntity;
import org.example.model.InitialUser;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TriviaService triviaService;


    @Transactional
    public UserDto createUser(InitialUser user) {
        UserEntity userDto = userMapper.createUserDto(user.getName());
        triviaService.fetchAndStoreQuestions();
        UserEntity save = userRepository.save(userDto);
        return userMapper.userEntityToUserDto(save);
    }

    @Transactional
    public UserEntity updateUserScore(String session, boolean isCorrect, Long questionId) {
        UserEntity userBySession = userRepository.findBySession(session);
        if (userBySession == null) {
            throw new RuntimeException("User not found");
        }

        userBySession.setQuestionsAnswered(userBySession.getQuestionsAnswered() + 1);
        if (isCorrect) {
            userBySession.setCorrectAnswers(userBySession.getCorrectAnswers() + 1);
        }
        userBySession.getAnsweredQuestions().add(questionId);

        return userRepository.save(userBySession);
    }

    public UserDto getUserBySession(String session) {
        UserEntity bySession = userRepository.findBySession(session);
        return  userMapper.userEntityToUserDto(bySession);
    }
//
//    public Question getQuestion(UUID sessionId) {
//        return triviaService.getNextQuestion();
//    }
}