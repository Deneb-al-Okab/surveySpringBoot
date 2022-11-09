package com.surveySpringBoot.repository;

import com.surveySpringBoot.model.Answer;
import com.surveySpringBoot.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, String> {

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "question_answer",
            joinColumns        = { @JoinColumn(name = "id_question") },
            inverseJoinColumns = { @JoinColumn(name = "id_answer") })
    List<Answer> answers = new ArrayList<>();


}