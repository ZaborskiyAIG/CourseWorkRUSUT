package com.CourseWorkRusut.dto;

import com.CourseWorkRusut.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property="nameRole", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StudentDTO.class, name = "ROLE_STUDENT"),
        @JsonSubTypes.Type(value = TeacherDTO.class, name = "ROLE_TEACHER"),
        @JsonSubTypes.Type(value = AdminDTO.class, name = "ROLE_ADMIN"),
        @JsonSubTypes.Type(value = UserDTO.class, name = "ROLE_USER")
})
public class UserDTO {

    private Long userId;

    private String name;

    private String surname;

    private String middlename;

    private String email;

    @JsonIgnore
    private Role role;

    @JsonTypeId
    private String nameRole;

    public UserDTO(Long userId, String name, String surname, String middlename, String email, String nameRole) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.email = email;
        this.nameRole = nameRole;
    }

    public UserDTO(Long userId, String name, String surname, String middlename){
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.middlename =middlename;
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

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getNameRole() {
        return nameRole;
    }

}
