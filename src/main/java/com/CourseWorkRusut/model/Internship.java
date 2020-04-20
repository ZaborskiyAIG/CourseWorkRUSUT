package com.CourseWorkRusut.model;

import javax.persistence.*;

@Entity
@Table(name = "internship")
public class Internship {

    @Id
    @Column(name = "internship_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    @ManyToOne
    @JoinColumn(name = "Place_practice_id")
    private PlacePractice placePractice;

    @Column(name = "internship_scientific_director")
    private String internshipScientificDirector;

    @ManyToOne
    @JoinColumn(name = "Semester_id")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "Teacher_id")
    private Teacher teacher;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "report", column = @Column(name = "report")),
            @AttributeOverride( name = "mark", column = @Column(name = "mark")),
    })
    EmbeddableLearningInternship embeddableLearningInternship;

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public PlacePractice getPlacePractice() {
        return placePractice;
    }

    public void setPlacePracticle(PlacePractice placePracticle) {
        this.placePractice = placePractice;
    }

    public String getInternshipScientificDirector() {
        return internshipScientificDirector;
    }

    public void setInternshipScientificDirector(String internshipScientificDirector) {
        this.internshipScientificDirector = internshipScientificDirector;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
