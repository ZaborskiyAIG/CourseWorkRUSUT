package com.CourseWorkRusut.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @Column(name = "Author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "middlename")
    private String middlename;

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

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(authorId, author.authorId) &&
                Objects.equals(name, author.name) &&
                Objects.equals(surname, author.surname) &&
                Objects.equals(middlename, author.middlename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, name, surname, middlename);
    }
}
