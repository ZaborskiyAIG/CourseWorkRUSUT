package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.DTO.LearningActivitiesDTO;
import com.CourseWorkRusut.model.LearningActivities;

import java.util.List;

public interface LearningActivitiesDAO {

    List<LearningActivitiesDTO> getAllLearningActivities();

    void delete(LearningActivities learningActivities);

}
