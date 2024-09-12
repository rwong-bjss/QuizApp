package org.example.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stored_questions")
public class StoredQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String difficulty;
    private String category;
    private String question;
    private String correctAnswer;

    @ElementCollection
    @CollectionTable(name = "incorrect_answers", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "incorrect_answer")
    private List<String> incorrectAnswers;

    private boolean answered = false;
}