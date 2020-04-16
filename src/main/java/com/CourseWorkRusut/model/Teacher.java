package com.CourseWorkRusut.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "teacher")
@PrimaryKeyJoinColumn(name = "Teacher_id")
public class Teacher extends User {

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_science_degree",
            joinColumns = { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "position_id")})
    private List<Position> positions;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_science_degree",
            joinColumns = { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "science_degree_id")})
    private List<ScienceDegree> scienceDegrees;

    @OneToMany(mappedBy = "teacher")
    private List<SubjectTeacherGroup> subjectTeacherGroups;

    @OneToMany(mappedBy = "teacher")
    private List<Exam> exams;

    @OneToMany(mappedBy = "teacher")
    private List<LearningActivities> learningActivities;

    @OneToMany(mappedBy = "teacher")
    private List<Internship> internships;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public List<ScienceDegree> getScienceDegrees() {
        return scienceDegrees;
    }

    public void setScienceDegrees(List<ScienceDegree> scienceDegrees) {
        this.scienceDegrees = scienceDegrees;
    }

    public List<SubjectTeacherGroup> getSubjectTeacherGroups() {
        return subjectTeacherGroups;
    }

    public void setSubjectTeacherGroups(List<SubjectTeacherGroup> subjectTeacherGroups) {
        this.subjectTeacherGroups = subjectTeacherGroups;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<LearningActivities> getLearningActivities() {
        return learningActivities;
    }

    public void setLearningActivities(List<LearningActivities> learningActivities) {
        this.learningActivities = learningActivities;
    }

    public List<Internship> getInternships() {
        return internships;
    }

    public void setInternships(List<Internship> internships) {
        this.internships = internships;
    }

}
