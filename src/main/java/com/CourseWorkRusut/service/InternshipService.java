package com.CourseWorkRusut.service;

import com.CourseWorkRusut.DTO.InternshipDTO;
import com.CourseWorkRusut.DTO.LearningActivitiesDTO;
import com.CourseWorkRusut.model.Internship;
import com.CourseWorkRusut.model.LearningActivities;
import com.CourseWorkRusut.model.PlacePractice;

import java.util.List;

public interface InternshipService {

    List<InternshipDTO> getAllInternships(String offset);

    void delete(Long id);

    void deletePlace(Long id);

    void save(Internship internship);

    void save(PlacePractice placePractice);

    List<PlacePractice> getAllPlace(String offset);

    List<PlacePractice> getPlaceBySearch(String search);

    List<PlacePractice> getAllPlace();

    PlacePractice getPlace(Long id);

    void updatePlace(PlacePractice placePractice);

    List<InternshipDTO> getInternshipsBySearch(String search);

    Long counterInternshipBySearch(String search);

    Long counterPlace();

    Long counterPlaceBySearch(String search);

    Long counterInternship();

    List<InternshipDTO> getInternshipsByStudent(Long id);

    List<LearningActivitiesDTO> getLearningsByStudent(Long id);

    Internship getInternshipsById(Long id);

    LearningActivities getLearningById(Long id);

    void update(Internship internship);

    void update(LearningActivities learningActivities);

    List<InternshipDTO> getInternshipsByTeacher(Long id);

    List<LearningActivitiesDTO> getLearningsByTeacher(Long id);

}
