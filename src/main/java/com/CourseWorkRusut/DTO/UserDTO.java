package com.CourseWorkRusut.DTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="typeUser")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StudentDTO.class, name = "Student"),
        @JsonSubTypes.Type(value = TeacherDTO.class, name = "Teacher"),
        @JsonSubTypes.Type(value = AdminDTO.class, name = "Admin"),
        @JsonSubTypes.Type(value = UserDTO.class, name = "User")
})
public class UserDTO {

    private Long userId;

    private String name;

    private String surname;

    private String middlename;

    private String email;

    private String role;

    public UserDTO(Long userId, String name, String surname, String middlename, String email) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.email = email;
    }

    public UserDTO(){

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
