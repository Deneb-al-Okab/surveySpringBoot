package com.surveySpringBoot.repository;

import com.surveySpringBoot.model.SubmittedAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmittedAnswerRepository extends JpaRepository<SubmittedAnswer, Long> {
}