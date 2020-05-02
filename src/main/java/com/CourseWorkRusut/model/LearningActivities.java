package com.CourseWorkRusut.model;

import javax.persistence.*;

@Entity
@Table(name = "learning_activities")
public class LearningActivities {

    @Id
    @Column(name = "learning_activities_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long learningId;

    @ManyToOne
    @JoinColumn(name = "Semester_id")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "learning_activities_type_id")
    private LearningActivitiesType learningActivitiesType;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "report", column = @Column(name = "report")),
            @AttributeOverride( name = "mark", column = @Column(name = "mark")),
            @AttributeOverride( name = "topic", column = @Column(name = "topic")),
    })
    private EmbeddableLearningInternship embeddableLearningInternship;

    public Long getLearningId() {
        return learningId;
    }

    public void setLearningId(Long learningId) {
        this.learningId = learningId;
    }

    public EmbeddableLearningInternship getEmbeddableLearningInternship() {
        return embeddableLearningInternship;
    }

    public void setEmbeddableLearningInternship(EmbeddableLearningInternship embeddableLearningInternship) {
        this.embeddableLearningInternship = embeddableLearningInternship;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public LearningActivitiesType getLearningActivitiesType() {
        return learningActivitiesType;
    }

    public void setLearningActivitiesType(LearningActivitiesType learningActivitiesType) {
        this.learningActivitiesType = learningActivitiesType;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
