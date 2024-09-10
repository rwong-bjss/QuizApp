package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Column(unique = true)
    private String session;
    
    private Integer correctAnswers;
    
    private Integer questionsAnswered;
    
    @ElementCollection
    @CollectionTable(name = "user_answered_questions", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "question_id")
    private List<Long> answeredQuestions;
}