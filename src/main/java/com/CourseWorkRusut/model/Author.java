package com.CourseWorkRusut.model;

import javax.persistence.*;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @Column(name = "Author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long authorId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "midlename")
    private String midlename;

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMidlename() {
        return midlename;
    }

    public void setMidlename(String midlename) {
        this.midlename = midlename;
    }
}
