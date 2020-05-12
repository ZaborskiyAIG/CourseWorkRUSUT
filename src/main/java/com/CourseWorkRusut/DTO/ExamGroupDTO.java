package com.CourseWorkRusut.DTO;

import java.util.List;

public class ExamGroupDTO {

    private String typeExam;

    private String hours;

    private String group;

    private String subject;

    private String semester;

    private List<StudentExamDTO> students;

    public ExamGroupDTO(String typeExam, String group, String subject) {
        this.typeExam = typeExam;
        this.group = group;
        this.subject = subject;
    }

    public ExamGroupDTO() {

    }

    public String getTypeExam() {
        return typeExam;
    }

    public void setTypeExam(String typeExam) {
        this.typeExam = typeExam;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<StudentExamDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentExamDTO> students) {
        this.students = students;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semesters) {
        this.semester = semesters;
    }
}
