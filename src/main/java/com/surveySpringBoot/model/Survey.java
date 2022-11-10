package com.surveySpringBoot.model;
import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "survey_table")

public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_mail")
    private String idMail;
    @OneToOne(optional = false)
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "publish_date")
    private Calendar publishDate;

    @Column(name = "ending_date")
    private Calendar endingDate;

    public Survey(String idMail, Category category, String name, String description, Calendar publishDate, Calendar endingDate) {
        this.idMail = idMail;
        this.category = category;
        this.name = name;
        this.description = description;
        this.publishDate = publishDate;
        this.endingDate = endingDate;
    }

    public Survey() {
    }

    public long getId() {
        return id;
    }

    public String getIdMail() {
        return idMail;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Calendar getPublishDate() {
        return publishDate;
    }

    public Calendar getEndingDate() {
        return endingDate;
    }

    public void setIdMail(String idMail) {
        this.idMail = idMail;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublishDate(Calendar publishDate) {
        this.publishDate = publishDate;
    }


    public void setEndingDate(Calendar endingDate) {
        this.endingDate = endingDate;
    }
    @Override
    public  String toString() {
        return "Survey [id=" + id + ", name=" + name + "category " + category;
    }
}
