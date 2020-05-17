package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.DTO.InternshipDTO;
import com.CourseWorkRusut.DTO.LearningActivitiesDTO;
import com.CourseWorkRusut.model.Internship;
import com.CourseWorkRusut.model.LearningActivities;
import com.CourseWorkRusut.model.PlacePractice;

import java.util.List;

public interface InternshipDAO {

    List<InternshipDTO> getAllInternships();

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

}
