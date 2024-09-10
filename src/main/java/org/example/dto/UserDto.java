package org.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String name;
    private UUID sessionId;
    private Integer correctAnswers;
    private Integer questionsAnswered;
}