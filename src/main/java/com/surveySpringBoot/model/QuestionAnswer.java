package com.surveySpringBoot.model;

import javax.persistence.*;

@Entity
@Table(name = "question_answer")
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_question", nullable = false, insertable = false, updatable = false)
    private Question question;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_answer", nullable = false, insertable = false, updatable = false)
    private Answer answer;

    public QuestionAnswer(int id, Question question, Answer answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public QuestionAnswer() {

    }

    public long getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }
}