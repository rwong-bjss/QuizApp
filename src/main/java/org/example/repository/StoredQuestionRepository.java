package org.example.repository;

import org.example.entity.StoredQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoredQuestionRepository extends JpaRepository<StoredQuestion, Long> {
    StoredQuestion findFirstByAnsweredFalse();
    Optional<StoredQuestion> findByQuestion(String question);
}