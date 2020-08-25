package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.dao.SubjectDAO;
import com.CourseWorkRusut.model.Subject;
import com.CourseWorkRusut.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectDAO subjectDAO;

    @Autowired
    public SubjectServiceImpl(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    @Override
    @Transactional
    public Subject getSubjectByName(String nameSubject) {
        return subjectDAO.getSubjectByName(nameSubject);
    }

    @Override
    @Transactional
    public List<Subject> getSubjectByTeacher(Long idTeacher) {
        return subjectDAO.getSubjectByTeacher(idTeacher);
    }

    @Override
    @Transactional
    public List<String> getAllSubject(){
        return subjectDAO.getAllSubject();
    }
}
