package com.surveySpringBoot.repository;

import com.surveySpringBoot.model.SubmittedSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmittedSurveyRepository extends JpaRepository<SubmittedSurvey, Long> {
}