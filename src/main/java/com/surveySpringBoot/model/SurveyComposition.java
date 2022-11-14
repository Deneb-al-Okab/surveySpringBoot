package com.surveySpringBoot.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@IdClass(SurveyCompositionPK.class)
@Entity
@Table(name = "survey_composition")
public class SurveyComposition {

    @Id
    @Column(name = "id_survey")
    private Long id_survey;

    @Id
    @Column(name = "id_question_answer")
    private Long id_question_answer;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_survey", nullable = false, insertable = false, updatable = false)
    private Survey survey;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_question_answer", nullable = false, insertable = false, updatable = false)
    private QuestionAnswer question_answer;

    public SurveyComposition () { }
    public SurveyComposition (Long id_survey, Long id_question_answer) {
        this.id_survey = id_survey;
        this.id_question_answer = id_question_answer;
    }
    public Long getId_survey () { return this.id_survey; }
    public Long getId_question_answer () { return this.id_question_answer; }
    public void setId_survey (Long id_survey) { this.id_survey = id_survey; }
    public void setId_question_answer (Long id_question_answer) { this.id_question_answer = id_question_answer; }
}

class SurveyCompositionPK implements Serializable {
    private Long id_survey;
    private Long id_question_answer;

    public SurveyCompositionPK () { }
    public SurveyCompositionPK(Long id_survey, Long id_question_answer) {
        this.id_survey = id_survey;
        this.id_question_answer = id_question_answer;
    }
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurveyCompositionPK SCPK = (SurveyCompositionPK) o;
        return this.id_survey.equals(SCPK.id_survey) && this.id_question_answer.equals(SCPK.id_question_answer);
    }
    @Override
    public int hashCode () {
        System.out.println(Objects.hash(this.id_survey, this.id_question_answer));
        return Objects.hash(this.id_survey, this.id_question_answer);
    }

}