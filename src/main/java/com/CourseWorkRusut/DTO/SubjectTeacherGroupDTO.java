package com.CourseWorkRusut.DTO;

import java.util.List;

public class SubjectTeacherGroupDTO {

    private List<String> groups;

    private String subject;

    public SubjectTeacherGroupDTO(List<String> groups, String subject) {
        this.groups = groups;
        this.subject = subject;
    }

    public SubjectTeacherGroupDTO() {
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}