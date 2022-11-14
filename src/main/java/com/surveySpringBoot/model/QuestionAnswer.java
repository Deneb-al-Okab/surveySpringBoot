package com.surveySpringBoot.model;

import javax.persistence.*;

@Entity
@Table(name = "question_answer")
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="id_question")
    private long id_question;

    @Column(name="id_answer")
    private long id_answer;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_question", nullable = false, insertable = false, updatable = false)
    private Question question;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_answer", nullable = false, insertable = false, updatable = false)
    private Answer answer;

    public QuestionAnswer(long id_question, long id_answer) {
        this.id_question = id_question;
        this.id_answer = id_answer;
    }

    public QuestionAnswer() {

    }

    public long getId() {
        return id;
    }

    public long getId_question() {
        return id_question;
    }

    public long getId_answer() {
        return id_answer;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }
}