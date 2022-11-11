package com.surveySpringBoot.repository;

import com.surveySpringBoot.model.SurveyComposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyCompositionRepository extends JpaRepository<SurveyComposition, Long> {
    @Query(value="select s from survey_composition s where s.id_survey = ?1 ; ", nativeQuery = true)
    List<SurveyComposition> findById_survey(Long id_survey);
}