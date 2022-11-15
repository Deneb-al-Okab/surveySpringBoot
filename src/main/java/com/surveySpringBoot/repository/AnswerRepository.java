package com.surveySpringBoot.repository;

import com.surveySpringBoot.model.Answer;
import com.surveySpringBoot.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, String> {
    Page<Answer> findAll(Pageable pageable);

    @Query("select a from Answer a where a.answer = ?1")
    Optional<Answer> findByAnswer(String answer);

    @Query("select a from Answer a where upper(a.answer) = upper(?1)")
    Optional<Answer> findByAnswerIgnoreCase(String answer);






}