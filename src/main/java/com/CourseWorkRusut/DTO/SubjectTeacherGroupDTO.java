package com.CourseWorkRusut.DTO;

import java.util.List;

public class SubjectTeacherGroupDTO {

    private List<String> groups;

    private String subject;

    public SubjectTeacherGroupDTO(List<String> groups, String subject) {
        this.groups = groups;
        this.subject = subject;
    }
}
