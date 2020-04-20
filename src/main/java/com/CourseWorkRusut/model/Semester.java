package com.CourseWorkRusut.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "semester")
public class Semester {

    @Id
    @Column(name = "Semester_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long semestrId;

    @Column(name = "number_semester")
    private String numberSemestr;

    @OneToMany(mappedBy ="semester", fetch=FetchType.LAZY)
    private List<LearningActivities> learningActivities;

    @OneToMany(mappedBy = "semester", fetch=FetchType.LAZY)
    private List<Internship> internships;

    @OneToMany(mappedBy = "semester", fetch=FetchType.LAZY)
    private List<Exam> exam;

    @ManyToOne
    @JoinColumn (name="student_id")
    private Student student;


    public long getSemestrId() {
        return semestrId;
    }

    public void setSemestrId(long semestrId) {
        this.semestrId = semestrId;
    }

    public String getNumberSemestr() {
        return numberSemestr;
    }

    public void setNumberSemestr(String numberSemestr) {
        this.numberSemestr = numberSemestr;
    }
}
