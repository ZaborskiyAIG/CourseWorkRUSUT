package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.SubjectDAO;
import com.CourseWorkRusut.model.Subject;
import com.CourseWorkRusut.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectDAO subjectDAO;

    @Autowired
    public SubjectServiceImpl(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    @Override
    public Subject getSubjectByName(String nameSubject) {
        return subjectDAO.getSubjectByName(nameSubject);
    }

    @Override
    public List<Subject> getSubjectByTeacher(Long idTeacher) {
        return subjectDAO.getSubjectByTeacher(idTeacher);
    }
}
