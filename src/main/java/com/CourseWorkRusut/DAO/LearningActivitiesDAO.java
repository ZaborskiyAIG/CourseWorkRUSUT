package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.DTO.LearningActivitiesDTO;
import com.CourseWorkRusut.model.LearningActivities;
import com.CourseWorkRusut.model.LearningActivitiesType;

import java.util.List;

public interface LearningActivitiesDAO {

    List<LearningActivitiesDTO> getAllLearningActivities(String offset);

    void delete(LearningActivities learningActivities);

    List<String> getTypeLearning();

    LearningActivitiesType getLearningByType(String type);
}
