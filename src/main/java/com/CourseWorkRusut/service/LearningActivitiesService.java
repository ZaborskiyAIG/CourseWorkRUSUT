package com.CourseWorkRusut.service;

import com.CourseWorkRusut.DTO.LearningActivitiesDTO;

import java.util.List;

public interface LearningActivitiesService {

    List<LearningActivitiesDTO> getAllLearningActivities();

    void delete(Long id);

}
