package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.ExamDAO;
import com.CourseWorkRusut.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamDAO examDAO;



}
