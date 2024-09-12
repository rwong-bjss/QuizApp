package org.example.mapper;

import java.util.ArrayList;
import org.example.entity.StoredQuestion;
import org.example.model.Question;
import org.example.model.UserEntity;
import org.example.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "sessionId", source = "session")
    UserDto userEntityToUserDto(UserEntity userEntity);

    @Mapping(target = "session", source = "sessionId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "answeredQuestions", ignore = true)
    UserEntity userDtoToUserEntity(UserDto userDto);

    default UUID map(String value) {
        return value != null ? UUID.fromString(value) : null;
    }

    default String map(UUID value) {
        return value != null ? value.toString() : null;
    }

    default UserEntity createUserDto(String name ) {
        UserEntity user = new UserEntity();
        user.setName(name);
        user.setSession(UUID.randomUUID().toString());
        user.setCorrectAnswers(0);
        user.setQuestionsAnswered(0);
        user.setAnsweredQuestions(new ArrayList<>());
        return user;
    }
}