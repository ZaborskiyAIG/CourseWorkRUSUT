package com.CourseWorkRusut.DTO;

public class LearningActivitiesDTO {

    private Long learningActivitiesId;

    private String mark;

    private String numberSemester;

    private String nameType;

    private String nameTeacher;

    private Long userId;

    private String topic;

    public LearningActivitiesDTO(Long learningActivitiesId,
                                 String nameType,
                                 String numberSemester,
                                 String nameTeacher,
                                 String mark,
                                 Long userId,
                                 String topic) {
        this.learningActivitiesId = learningActivitiesId;
        this.mark = mark;
        this.numberSemester = numberSemester;
        this.nameType = nameType;
        this.nameTeacher = nameTeacher;
        this.userId = userId;
        this.topic =topic;
    }

    public LearningActivitiesDTO(){

    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Long getLearningActivitiesId() {
        return learningActivitiesId;
    }

    public void setLearningActivitiesId(Long learningActivitiesId) {
        this.learningActivitiesId = learningActivitiesId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getNumberSemester() {
        return numberSemester;
    }

    public void setNumberSemester(String numberSemester) {
        this.numberSemester = numberSemester;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
    }
}
