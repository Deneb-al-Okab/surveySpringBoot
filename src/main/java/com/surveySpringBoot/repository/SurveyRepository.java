package com.surveySpringBoot.repository;

import com.surveySpringBoot.model.Answer;
import com.surveySpringBoot.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, String> {

    @Query(
            value = "select * " +
                    "from survey_table " +
                    "where survey_table.id not in (" +
                    "SELECT survey_table.id " +
                    "FROM submitted_survey " +
                    "left join survey_table on submitted_survey.id_survey = survey_table.id " +
                    "where submitted_survey.id_mail = ?1 ) " +
                    "limit ?2 , ?3 ;",
            nativeQuery = true)
    List<Survey> SurveyToDo(String id_mail, Integer start, Integer step);

    @Query(
            value = "SELECT * " +
                    "FROM survey_table " +
                    "left join submitted_survey on submitted_survey.id_survey = survey_table.id " +
                    "WHERE submitted_survey.id_mail = ?1 " +
                    "limit ?2 , ?3 ; ",
            nativeQuery = true)
    List<Survey> SurveyDone(String id_mail, Integer start, Integer step);



}