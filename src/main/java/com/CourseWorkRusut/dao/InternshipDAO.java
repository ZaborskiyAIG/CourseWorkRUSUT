package com.CourseWorkRusut.dao;

import com.CourseWorkRusut.dto.InternshipDTO;
import com.CourseWorkRusut.dto.LearningActivitiesDTO;
import com.CourseWorkRusut.model.Internship;
import com.CourseWorkRusut.model.LearningActivities;
import com.CourseWorkRusut.model.PlacePractice;

import java.util.List;

public interface InternshipDAO {

    List<InternshipDTO> getAllInternships(String offset);

    void delete(Internship internship);

    void save(Internship internship);

    void save(PlacePractice placePractice);

    List<PlacePractice> getAllPlace(String offset);

    List<PlacePractice> getAllPlace();

    PlacePractice getPlace(Long id);

    void updatePlace(PlacePractice placePractice);

    void deletePlace(PlacePractice placePractice);

    Long counterPlace();

    List<InternshipDTO> getInternshipsByStudent(Long id);

    List<LearningActivitiesDTO> getLearningsByStudent(Long id);

    Internship getInternshipsById(Long id);

    void update(Internship internship);

    void update(LearningActivities learningActivities);

    LearningActivities getLearningById(Long id);

    List<InternshipDTO> getInternshipsByTeacher(Long id);

    List<LearningActivitiesDTO> getLearningsByTeacher(Long id);

    Long counterInternship();

    List<InternshipDTO> getInternshipsBySearch(String search);

    Long counterInternshipBySearch(String search);

    List<PlacePractice> getPlaceBySearch(String search);

    Long counterPlaceBySearch(String search);

}
