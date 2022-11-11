package com.surveySpringBoot.model;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(SubmittedAnswerPK.class)
@Entity
@Table(name = "submitted_answer")
public class SubmittedAnswer {
    @Id
    @Column(name = "id_submitted_survey")
    private Long id_submitted_survey;

    @Id
    @Column(name = "id_question_answer")
    private Long id_question_answer;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_submitted_survey", nullable = false, insertable = false, updatable = false)
    private SubmittedSurvey submitted_survey;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_question_answer", nullable = false, insertable = false, updatable = false)
    private QuestionAnswer question_answer;

    public SubmittedAnswer() {

    }

    public SubmittedAnswer(Long id_submitted_survey, Long id_question_answer) {
        this.id_submitted_survey = id_submitted_survey;
        this.id_question_answer = id_question_answer;
    }

    public Long getId_submitted_survey() {
        return id_submitted_survey;
    }

    public void setId_submitted_survey(Long id_submitted_survey) {
        this.id_submitted_survey = id_submitted_survey;
    }

    public Long getId_question_answer() {
        return id_question_answer;
    }

    public void setId_question_answer(Long id_question_answer) {
        this.id_question_answer = id_question_answer;
    }
}
class SubmittedAnswerPK implements Serializable {
    private Long id_submitted_survey;
    private Long id_question_answer;

    public SubmittedAnswerPK () { }
    public SubmittedAnswerPK(Long id_submitted_survey, Long id_question_answer) {
        this.id_submitted_survey = id_submitted_survey;
        this.id_question_answer = id_question_answer;
    }
}