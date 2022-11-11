package com.surveySpringBoot.model;

import javax.persistence.*;

@Entity
@Table(name = "submitted_survey")
public class SubmittedSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="id_survey")
    private long idSurvey;

    @Column(name="id_mail")
    private String idMail;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_survey", nullable = false, insertable = false, updatable = false)
    private Survey survey;

    public SubmittedSurvey(long id_survey, String id_mail) {
        this.idSurvey = id_survey;
        this.idMail = id_mail;
    }

    public SubmittedSurvey() {


    }

    public long getId() {
        return id;
    }

    public long getIdSurvey() {
        return idSurvey;
    }

    public String getIdMail() {
        return idMail;
    }


    public void setIdSurvey(long id_survey) {
        this.idSurvey = id_survey;
    }

    public void setIdMail(String id_mail) {
        this.idMail = id_mail;
    }
}