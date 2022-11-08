package com.surveySpringBoot.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "users")

public class User {

    @Id
    @Column(name = "mail", unique=true)
    private String mail;

    @Column(name = "pass")
    private String pass;
    @Column(name = "is_admin")
    private long isAdmin;

    public User() {

    }

    public User(String mail, String pass, long isAdmin) {
        this.mail    = mail;
        this.pass    = pass;
        this.isAdmin = isAdmin;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public long getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(long isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User [mail=" + mail + ", pass=" + pass + ", isAdmin=" + isAdmin + "]";
    }

}