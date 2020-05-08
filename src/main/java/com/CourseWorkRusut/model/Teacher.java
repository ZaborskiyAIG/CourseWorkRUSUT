package com.CourseWorkRusut.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "teacher")
@PrimaryKeyJoinColumn(name = "Teacher_id")
public class Teacher extends User {

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_science_degree",
            joinColumns = { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "position_id")})
    private Set<Position> positions;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_science_degree",
            joinColumns = { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "science_degree_id")})
    private Set<ScienceDegree> scienceDegrees;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<SubjectTeacherGroup> subjectTeacherGroups;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Exam> exams;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<LearningActivities> learningActivities;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Internship> internships;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    public Set<ScienceDegree> getScienceDegrees() {
        return scienceDegrees;
    }

    public void setScienceDegrees(Set<ScienceDegree> scienceDegrees) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(email, teacher.email) &&
                Objects.equals(positions, teacher.positions) &&
                Objects.equals(scienceDegrees, teacher.scienceDegrees) &&
                Objects.equals(subjectTeacherGroups, teacher.subjectTeacherGroups) &&
                Objects.equals(exams, teacher.exams) &&
                Objects.equals(learningActivities, teacher.learningActivities) &&
                Objects.equals(internships, teacher.internships);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, positions, scienceDegrees, subjectTeacherGroups, exams, learningActivities, internships);
    }
}
