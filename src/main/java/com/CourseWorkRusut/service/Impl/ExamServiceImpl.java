package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.ExamDAO;
import com.CourseWorkRusut.DTO.ExamGroupDTO;
import com.CourseWorkRusut.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamDAO examDAO;


    @Override
    @Transactional
    public void save(ExamGroupDTO exam) {

    }
}
