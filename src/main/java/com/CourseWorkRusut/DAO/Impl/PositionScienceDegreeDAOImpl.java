package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.PositionScienceDegreeDAO;
import com.CourseWorkRusut.model.Position;
import com.CourseWorkRusut.model.ScienceDegree;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PositionScienceDegreeDAOImpl implements PositionScienceDegreeDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public PositionScienceDegreeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<String> getAllPositions() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT position.namePosition FROM Position position",String.class).list();
    }

    @Override
    public List<String> getAllScienceDegree() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT scienceDegree.nameScienceDegree FROM ScienceDegree scienceDegree", String.class).list();
    }

    @Override
    public List<Position> getPositionsByByName(List<String> namePosition) {
        Session session = sessionFactory.getCurrentSession();
        Query<Position> query = session.createQuery("from Position position where position.namePosition in (:namePosition)", Position.class);
        query.setParameterList("namePosition",namePosition);
        return query.list();
    }

    @Override
    public List<ScienceDegree> getScienceDegreeByByName(List<String> nameScienceDegree) {
        Session session = sessionFactory.getCurrentSession();
        Query<ScienceDegree> query = session.createQuery("from ScienceDegree scienceDegree where scienceDegree.nameScienceDegree in (:nameScienceDegree)", ScienceDegree.class);
        query.setParameterList("nameScienceDegree",nameScienceDegree);
        return query.list();
    }
}
