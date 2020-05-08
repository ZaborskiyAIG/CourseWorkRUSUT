package com.CourseWorkRusut.service;

import com.CourseWorkRusut.model.Subject;

import java.util.List;

public interface SubjectService {

    Subject getSubjectByName(String nameSubject);

    List<Subject> getSubjectByTeacher(Long idTeacher);

    List<String> getAllSubject();

}
