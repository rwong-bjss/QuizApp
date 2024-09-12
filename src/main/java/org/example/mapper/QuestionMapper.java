package org.example.mapper;

import org.example.entity.StoredQuestion;
import org.example.model.Question;
import org.example.model.TriviaQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(target = "options", source = ".", qualifiedByName = "combineAnswers")
    Question storedQuestionToQuestion(StoredQuestion storedQuestion);

    @Mapping(target = "answered", constant = "false")
    StoredQuestion triviaQuestionToStoredQuestion(TriviaQuestion triviaQuestion);

    @Named("combineAnswers")
    default List<String> combineAnswers(StoredQuestion storedQuestion) {
        List<String> options = new ArrayList<>(storedQuestion.getIncorrectAnswers());
        options.add(storedQuestion.getCorrectAnswer());
        // Optionally shuffle the options here
        return options;
    }
}