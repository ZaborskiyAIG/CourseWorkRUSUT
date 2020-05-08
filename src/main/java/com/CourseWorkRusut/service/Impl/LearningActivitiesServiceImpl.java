package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.LearningActivitiesDAO;
import com.CourseWorkRusut.DTO.LearningActivitiesDTO;
import com.CourseWorkRusut.model.LearningActivities;
import com.CourseWorkRusut.service.LearningActivitiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LearningActivitiesServiceImpl implements LearningActivitiesService {

    private LearningActivitiesDAO learningActivitiesDAO;

    @Autowired
    public LearningActivitiesServiceImpl(LearningActivitiesDAO learningActivitiesDAO){
        this.learningActivitiesDAO = learningActivitiesDAO;
    }

    @Override
    @Transactional
    public List<LearningActivitiesDTO> getAllLearningActivities() {
        return learningActivitiesDAO.getAllLearningActivities();
    }

    @Override
    public void delete(Long id) {
        LearningActivities learningActivities = new LearningActivities();
        learningActivities.setLearningId(id);
        learningActivitiesDAO.delete(learningActivities);
    }
}
