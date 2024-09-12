package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class TriviaQuestion {
    private String type;
    private String difficulty;
    private String category;
    private String question;
    
    @JsonProperty("correct_answer")
    private String correctAnswer;
    
    @JsonProperty("incorrect_answers")
    private List<String> incorrectAnswers;
}