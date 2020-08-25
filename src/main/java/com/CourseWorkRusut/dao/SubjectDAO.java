package com.CourseWorkRusut.dao;

import com.CourseWorkRusut.model.Subject;

import java.util.List;

public interface SubjectDAO {

    Subject getSubjectByName(String nameSubject);

    List<Subject> getSubjectByTeacher(Long idTeacher);

    List<String> getAllSubject();

}
