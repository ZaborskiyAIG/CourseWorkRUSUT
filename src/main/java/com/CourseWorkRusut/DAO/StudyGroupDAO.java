package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.model.StudyGroup;

import java.util.List;

public interface StudyGroupDAO {

    Long addStudyGroup(StudyGroup studyGroup);

    List<StudyGroup> getAllStudyGroupByNameSpecialty(String nameSpecialty);

    Long getCountStudentsInGroup(String numberGroup);

    StudyGroup getStudyGroupById(Long id);

    StudyGroup getStudyGroupByNumberGroup(String numberGroup);
}
