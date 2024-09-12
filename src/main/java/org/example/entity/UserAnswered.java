package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "user_answered")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID sessionId;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private StoredQuestion question;

    @Column(nullable = false)
    private boolean correct;
}