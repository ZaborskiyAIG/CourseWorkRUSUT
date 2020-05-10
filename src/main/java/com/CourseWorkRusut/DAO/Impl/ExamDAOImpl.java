package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.ExamDAO;
import com.CourseWorkRusut.model.Exam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExamDAOImpl implements ExamDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public ExamDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Exam exam) {
        Session session = sessionFactory.getCurrentSession();
        session.save(exam);
    }
}
