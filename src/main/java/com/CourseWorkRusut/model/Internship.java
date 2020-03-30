package com.CourseWorkRusut.model;

import javax.persistence.*;

@Entity
@Table(name = "internship")
public class Internship {

    @Id
    @Column(name = "internship_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long examId;

    @Column(name = "place_practicle")
    private String placePracticle;

    @Column(name = "internship_director")
    private String internshipDirector;

 //   @Column(name = "mark")
  //  private String mark; //вынести в отдельный класс

   // @Column(name = "report")
   // private byte[] report;

    @Column(name = "internship_scientific_director")
    private String internshipScientificDirector;

    @ManyToOne
    @JoinColumn(name = "Semester_id")
    private Semester semester;

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

    public String getPlacePracticle() {
        return placePracticle;
    }

    public void setPlacePracticle(String placePracticle) {
        this.placePracticle = placePracticle;
    }

    public String getInternshipDirector() {
        return internshipDirector;
    }

    public void setInternshipDirector(String internshipDirector) {
        this.internshipDirector = internshipDirector;
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
}
