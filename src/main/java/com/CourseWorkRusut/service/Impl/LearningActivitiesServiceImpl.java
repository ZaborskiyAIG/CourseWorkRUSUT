package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.LearningActivitiesDAO;
import com.CourseWorkRusut.DTO.LearningActivitiesDTO;
import com.CourseWorkRusut.model.LearningActivities;
import com.CourseWorkRusut.model.LearningActivitiesType;
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
    public List<LearningActivitiesDTO> getAllLearningActivities(String offset) {
        return learningActivitiesDAO.getAllLearningActivities(offset);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LearningActivities learningActivities = new LearningActivities();
        learningActivities.setLearningId(id);
        learningActivitiesDAO.delete(learningActivities);
    }

    @Override
    @Transactional
    public List<String> getTypeLearning() {
        return learningActivitiesDAO.getTypeLearning();
    }

    @Override
    @Transactional
    public LearningActivitiesType getLearningByType(String type) {
        return learningActivitiesDAO.getLearningByType(type);
    }

    @Override
    @Transactional
    public List<LearningActivities> getLearningActivitiesBySearch(String search) {
        return learningActivitiesDAO.getLearningActivitiesBySearch(search);
    }

    @Override
    @Transactional
    public Long counterLearning() {
        return learningActivitiesDAO.counterLearning();
    }

    @Override
    @Transactional
    public Long counterLearningBySearch(String search) {
        return learningActivitiesDAO.counterLearningBySearch(search);
    }

    @Override
    @Transactional
    public void save(LearningActivities learningActivities) {
        learningActivitiesDAO.save(learningActivities);
    }
}
