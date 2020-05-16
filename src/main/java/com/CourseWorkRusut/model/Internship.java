package com.CourseWorkRusut.model;

import javax.persistence.*;

@Entity
@Table(name = "internship")
public class Internship {

    @Id
    @Column(name = "internship_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internshipId;

    @ManyToOne
    @JoinColumn(name = "Place_practice_id")
    private PlacePractice placePractice;

    @Column(name = "internship_scientific_director")
    private String internshipScientificDirector;

    @Column(name = "internship_director")
    private String internshipDirector;

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
            @AttributeOverride( name = "topic", column = @Column(name = "topic")),
    })
    private EmbeddableLearningInternship embeddableLearningInternship;


    public Long getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(Long internshipId) {
        this.internshipId = internshipId;
    }

    public void setPlacePractice(PlacePractice placePractice) {
        this.placePractice = placePractice;
    }

    public PlacePractice getPlacePractice() {
        return placePractice;
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

    public EmbeddableLearningInternship getEmbeddableLearningInternship() {
        return embeddableLearningInternship;
    }

    public void setEmbeddableLearningInternship(EmbeddableLearningInternship embeddableLearningInternship) {
        this.embeddableLearningInternship = embeddableLearningInternship;
    }

    public String getInternshipDirector() {
        return internshipDirector;
    }

    public void setInternshipDirector(String internshipDirector) {
        this.internshipDirector = internshipDirector;
    }
}
