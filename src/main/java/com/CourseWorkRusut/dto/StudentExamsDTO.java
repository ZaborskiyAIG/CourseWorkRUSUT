package com.CourseWorkRusut.dto;

public class StudentExamsDTO {

    private String subject;

    private String semester;

    private String mark;

    private String typeExam;

    public StudentExamsDTO(String subject, String semester, String mark, String typeExam) {
        this.subject = subject;
        this.semester = semester;
        this.mark = mark;
        this.typeExam = typeExam;
    }

    public StudentExamsDTO() {

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTypeExam() {
        return typeExam;
    }

    public void setTypeExam(String typeExam) {
        this.typeExam = typeExam;
    }
}
