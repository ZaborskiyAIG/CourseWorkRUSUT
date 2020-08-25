package com.CourseWorkRusut.dto;


import java.util.Objects;

public class AuthorDTO {

    private Long authorId;

    private String name;

    private String surname;

    private String midlename;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDTO authorDTO = (AuthorDTO) o;
        return Objects.equals(authorId, authorDTO.authorId) &&
                Objects.equals(name, authorDTO.name) &&
                Objects.equals(surname, authorDTO.surname) &&
                Objects.equals(midlename, authorDTO.midlename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, name, surname, midlename);
    }
}
