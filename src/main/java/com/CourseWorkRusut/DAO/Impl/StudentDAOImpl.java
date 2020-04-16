package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.StudentDAO;
import com.CourseWorkRusut.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public StudentDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Student> getAllStudentWithoutRecordNumber() {
        Session session = sessionFactory.getCurrentSession(); //прочитать про ScrollableResults и пакетную обработку
        Query<Student> query = session.createQuery("from Student where number_book=:number_book",Student.class);
        query.setParameter("number_book", 0);
        return query.list();
    }



}
