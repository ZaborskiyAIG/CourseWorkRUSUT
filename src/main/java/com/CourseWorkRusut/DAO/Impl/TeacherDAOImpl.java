package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.TeacherDAO;
import com.CourseWorkRusut.model.Teacher;
import com.CourseWorkRusut.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherDAOImpl implements TeacherDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public TeacherDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getTeachersByParameters(String offset,String position ,String degree) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<User> query = session.createQuery("select user  from User user join fetch user.positions pos join fetch user.scienceDegrees sci where (type(user) in :types) and (:position is null or pos.namePosition = :position) and (:degree is null or sci.nameScienceDegree = :degree)",User.class );

        query.setParameter("position",position);
        query.setParameter("degree",degree);
        query.setParameter("types", Teacher.class);
        query.setFirstResult(Integer.valueOf(offset));

        int quantityUsersForPagination = 25;
        query.setMaxResults(Integer.valueOf(offset)+quantityUsersForPagination);
        return query.list();
    }

}
