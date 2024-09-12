package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class QuestionAnswer {
    private Integer questionId;
    private Boolean correctAnswer;
}
