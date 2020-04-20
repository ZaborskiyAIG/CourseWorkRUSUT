package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.model.StudyGroup;

import java.util.List;

public interface StudyGroupDAO {

    long addStudyGroup(StudyGroup studyGroup);

    List getAllStudyGroupBySpecialty(Long specialtyId);

    long getCountStudentsInGroup(StudyGroup studyGroup);

    StudyGroup getStudyGroupById(Long id);

}
