package com.surveySpringBoot.model;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "answer")
    private String answer;

    public Answer() {

    }

    public Answer(String answer) {
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer [id=" + id + ", answer=" + answer + "]";
    }

}
