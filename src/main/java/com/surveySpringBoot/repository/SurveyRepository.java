package com.surveySpringBoot.repository;

import com.surveySpringBoot.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, String> {
}