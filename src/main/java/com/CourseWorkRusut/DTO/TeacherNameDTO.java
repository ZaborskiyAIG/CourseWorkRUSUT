package com.CourseWorkRusut.DTO;

public class TeacherNameDTO {

    private String fullName;

    private Long id;

    public TeacherNameDTO(String name, String surname, String middlename, Long id) {
        this.fullName = name+" "+surname+" "+middlename;
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
