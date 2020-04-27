package com.CourseWorkRusut.DTO;

import com.CourseWorkRusut.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private Role role;

    private String nameRole;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        setNameRole(role.getNameRole());
        this.role = role;
    }

    public String getNameRole() {
        return nameRole;
    }

    private void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }
}
