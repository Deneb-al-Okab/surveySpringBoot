package com.surveySpringBoot.model;

import javax.persistence.*;

@Entity
@Table(name = "submitted_survey")
public class SubmittedSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="id_survey")
    private long id_survey;

    @Column(name="id_mail")
    private String id_mail;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_survey", nullable = false, insertable = false, updatable = false)
    private Survey survey;

    public SubmittedSurvey(long id_survey, String id_mail) {
        this.id_survey = id_survey;
        this.id_mail = id_mail;
    }

    public SubmittedSurvey() {

    }

    public long getId() {
        return id;
    }

    public long getId_survey() {
        return id_survey;
    }

    public String getId_mail() {
        return id_mail;
    }


    public void setId_survey(long id_survey) {
        this.id_survey = id_survey;
    }

    public void setId_mail(String id_mail) {
        this.id_mail = id_mail;
    }
}