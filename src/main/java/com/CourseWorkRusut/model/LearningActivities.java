package com.CourseWorkRusut.model;

import javax.persistence.*;

@Entity
@Table(name = "learning_activities")
public class LearningActivities {

    @Id
    @Column(name = "learning_activities_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long learningActivitiesId;

    @Column(name = "scientific_director")
    private String scientificDirector;

    @ManyToOne
    @JoinColumn(name = "Semester_id")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "learning_activities_type_id")
    private LearningActivitiesType learningActivitiesType;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "report", column = @Column(name = "report")),
            @AttributeOverride( name = "mark", column = @Column(name = "mark")),
    })
    EmbeddableLearningInternship embeddableLearningInternship;

    public long getLearningActivitiesId() {
        return learningActivitiesId;
    }

    public void setLearningActivitiesId(long learningActivitiesId) {
        this.learningActivitiesId = learningActivitiesId;
    }

    public String getScientificDirector() {
        return scientificDirector;
    }

    public void setScientificDirector(String scientificDirector) {
        this.scientificDirector = scientificDirector;
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
}
