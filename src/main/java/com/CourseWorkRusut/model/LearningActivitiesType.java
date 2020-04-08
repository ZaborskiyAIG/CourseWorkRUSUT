package com.CourseWorkRusut.model;

import javax.persistence.*;

@Entity
@Table(name = "learning_activities_type")
public class LearningActivitiesType {

    @Id
    @Column(name = "learning_activities_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long learningActivitiesTypeId;

    @Column(name = "learning_activities_name" )
    private String nameType;

    public long getLearningActivitiesIdType() {
        return learningActivitiesTypeId;
    }

    public void setLearningActivitiesIdType(long learningActivitiesIdType) {
        this.learningActivitiesTypeId = learningActivitiesIdType;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }
}
