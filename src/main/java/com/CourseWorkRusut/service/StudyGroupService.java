package com.CourseWorkRusut.service;

import com.CourseWorkRusut.model.StudyGroup;

import java.util.List;

public interface StudyGroupService {

    StudyGroup getStudyGroupForAddStudent(String nameSpecialty,  String entryYear);

    StudyGroup getStudyGroupByNumberGroup(String numberGroup);

    Long getCountStudentsInGroup(StudyGroup studyGroup);

    List<String> getAllStudyGroupByNameSpecialty(String nameSpecialty);

    List<StudyGroup> getStudyGroupBySubject(String nameSubject, Long teacherId);
}
