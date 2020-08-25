package com.CourseWorkRusut.service;

import com.CourseWorkRusut.dto.LearningActivitiesDTO;
import com.CourseWorkRusut.model.LearningActivities;
import com.CourseWorkRusut.model.LearningActivitiesType;

import java.util.List;

public interface LearningActivitiesService {

    List<LearningActivitiesDTO> getAllLearningActivities(String offset);

    void delete(Long id);

    List<String> getTypeLearning();

    LearningActivitiesType getLearningByType(String type);

    List<LearningActivitiesDTO> getLearningActivitiesBySearch(String search);

    Long counterLearning();

    Long counterLearningBySearch(String search);

    void save(LearningActivities learningActivities);

}
