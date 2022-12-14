package com.surveySpringBoot.model;
import com.surveySpringBoot.model.QuestionAnswer;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "question")
    private String question;

    @Column(name = "id_category")
    private Long id_category;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "question_answer",
            joinColumns        = { @JoinColumn(name = "id_question") },
            inverseJoinColumns = { @JoinColumn(name = "id_answer") })
    private List<Answer> answers = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_category", nullable = false, insertable = false, updatable = false)
    private Category category;

    public Question() {

    }

    public Question(long id, String question, List<Answer> answers, Category category) {
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.category = category;
    }
    public Question( String question, Long id_category) {
        this.question = question;
        this.id_category = id_category;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getId_category() {
        return id_category;
    }

    public void setId_category(Long id_category) {
        this.id_category = id_category;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", question=" + question + "]";
    }

}
