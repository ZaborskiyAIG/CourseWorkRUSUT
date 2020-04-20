package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.SpecialtyDAO;
import com.CourseWorkRusut.model.Specialty;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpecialtyDAOImpl implements SpecialtyDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public SpecialtyDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Specialty getSpecialtyById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.load(Specialty.class,id);

    }

    @Override
    public List<Specialty> getAllSpecialty() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Specialty", Specialty.class).list();
    }

}
