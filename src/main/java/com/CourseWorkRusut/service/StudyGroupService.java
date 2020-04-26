package com.CourseWorkRusut.service;

import com.CourseWorkRusut.model.StudyGroup;

public interface StudyGroupService {

    StudyGroup getStudyGroupForAddStudent(String nameSpecialty,  String entryYear);

    StudyGroup getStudyGroupByNumberGroup(String numberGroup);

    Long getCountStudentsInGroup(StudyGroup studyGroup);
}
