package com.CourseWorkRusut.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "semester")
public class Semester {

    @Id
    @Column(name = "Semester_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long semesterId;

    @Column(name = "number_semester")
    private String numberSemester;

    @OneToMany(mappedBy ="semester", fetch=FetchType.LAZY)
    private List<LearningActivities> learningActivities;

    @OneToMany(mappedBy = "semester", fetch=FetchType.LAZY)
    private List<Internship> internships;

    @OneToMany(mappedBy = "semester", fetch=FetchType.LAZY)
    private List<Exam> exam;

    @ManyToOne
    @JoinColumn (name="student_id")
    private Student student;


    public long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(long semestrId) {
        this.semesterId = semestrId;
    }

    public String getNumberSemester() {
        return numberSemester;
    }

    public void setNumberSemester(String numberSemestr) {
        this.numberSemester = numberSemestr;
    }
}
