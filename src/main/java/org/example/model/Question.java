package org.example.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Long id;
    private String type;
    private String difficulty;
    private String category;
    private String question;
    private List<String> options;
    // You might want to remove correctAnswer from here if you don't want to expose it directly
}
