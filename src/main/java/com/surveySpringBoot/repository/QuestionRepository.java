package com.surveySpringBoot.repository;

import com.surveySpringBoot.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory_Id(long id);

    @Query("select q from Question q where upper(q.question) = upper(?1)")
    Optional<Question> findByQuestionIgnoreCase(String question);


}

