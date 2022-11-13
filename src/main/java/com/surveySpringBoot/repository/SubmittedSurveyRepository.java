package com.surveySpringBoot.repository;

import com.surveySpringBoot.model.SubmittedSurvey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubmittedSurveyRepository extends JpaRepository<SubmittedSurvey, Long> {
    Page<SubmittedSurvey> findAll(Pageable pageable);

    @Query("select s from SubmittedSurvey s where s.idSurvey = ?1 and s.idMail = ?2")
    Optional<SubmittedSurvey> findByIdSurveyAndIdMail(long idSurvey, String idMail);




}