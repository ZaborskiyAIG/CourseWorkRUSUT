package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.InternshipDAO;
import com.CourseWorkRusut.DTO.InternshipDTO;
import com.CourseWorkRusut.DTO.LearningActivitiesDTO;
import com.CourseWorkRusut.model.Internship;
import com.CourseWorkRusut.model.LearningActivities;
import com.CourseWorkRusut.model.PlacePractice;
import com.CourseWorkRusut.service.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class InternshipServiceImpl implements InternshipService {

    private InternshipDAO internshipDAO;

    @Autowired
    public InternshipServiceImpl(InternshipDAO internshipDAO) {
        this.internshipDAO = internshipDAO;
    }

    @Override
    @Transactional
    public List<InternshipDTO> getAllInternships(String offset) {
        return internshipDAO.getAllInternships(offset);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Internship internship = new Internship();
        internship.setInternshipId(id);
        internshipDAO.delete(internship);
    }

    @Override
    public void deletePlace(Long id) {
        PlacePractice placePractice = new PlacePractice();
        placePractice.setPlacePracticeId(id);
        internshipDAO.deletePlace(placePractice);
    }

    @Override
    @Transactional
    public void save(Internship internship) {
        internshipDAO.save(internship);
    }

    @Override
    @Transactional
    public void save(PlacePractice placePractice) {
        internshipDAO.save(placePractice);
    }

    @Override
    @Transactional
    public List<PlacePractice> getAllPlace(String offset) {
        return internshipDAO.getAllPlace(offset);
    }

    @Override
    @Transactional
    public List<PlacePractice> getAllPlace() {
        return internshipDAO.getAllPlace();
    }

    @Override
    @Transactional
    public PlacePractice getPlace(Long id) {
        return internshipDAO.getPlace(id);
    }

    @Override
    @Transactional
    public void updatePlace(PlacePractice placePractice) {
         internshipDAO.updatePlace(placePractice);
    }

    @Override
    @Transactional
    public List<InternshipDTO> getInternshipsBySearch(String search) {
        return internshipDAO.getInternshipsBySearch(search);
    }

    @Override
    @Transactional
    public Long counterInternshipBySearch(String search) {
        return internshipDAO.counterInternshipBySearch(search);
    }

    @Override
    @Transactional
    public Long counterPlace() {
        return internshipDAO.counterPlace();
    }

    @Override
    @Transactional
    public Long counterInternship() {
        return internshipDAO.counterInternship();
    }

    @Override
    @Transactional
    public List<InternshipDTO> getInternshipsByStudent(Long id) {
        return internshipDAO.getInternshipsByStudent(id);
    }

    @Override
    @Transactional
    public List<LearningActivitiesDTO> getLearningsByStudent(Long id) {
        return internshipDAO.getLearningsByStudent(id);
    }

    @Override
    @Transactional
    public Internship getInternshipsById(Long id) {
        return internshipDAO.getInternshipsById(id);
    }

    @Override
    @Transactional
    public LearningActivities getLearningById(Long id) {
        return internshipDAO.getLearningById(id);
    }

    @Override
    @Transactional
    public void update(Internship internship) {
        internshipDAO.update(internship);
    }

    @Override
    @Transactional
    public void update(LearningActivities learningActivities) {
        internshipDAO.update(learningActivities);
    }

    @Override
    @Transactional
    public List<InternshipDTO> getInternshipsByTeacher(Long id) {
        return internshipDAO.getInternshipsByTeacher(id);
    }

    @Override
    @Transactional
    public List<LearningActivitiesDTO> getLearningsByTeacher(Long id) {
        return internshipDAO.getLearningsByTeacher(id);
    }


}
