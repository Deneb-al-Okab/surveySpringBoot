package com.surveySpringBoot.repository;

import com.surveySpringBoot.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory_Id(long id);

}

