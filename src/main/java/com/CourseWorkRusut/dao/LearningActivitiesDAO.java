package com.CourseWorkRusut.dao;

import com.CourseWorkRusut.dto.LearningActivitiesDTO;
import com.CourseWorkRusut.model.LearningActivities;
import com.CourseWorkRusut.model.LearningActivitiesType;

import java.util.List;

public interface LearningActivitiesDAO {

    List<LearningActivitiesDTO> getAllLearningActivities(String offset);

    void delete(LearningActivities learningActivities);

    List<String> getTypeLearning();

    LearningActivitiesType getLearningByType(String type);

    Long counterLearning();

    void save(LearningActivities learningActivities);

    List<LearningActivitiesDTO> getLearningActivitiesBySearch(String search);

    Long counterLearningBySearch(String search);
}
