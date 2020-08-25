package com.CourseWorkRusut.dao.Impl;

import com.CourseWorkRusut.dao.RoleDAO;
import com.CourseWorkRusut.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public RoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role getRoleByByName(String nameRole) {
        Session session = sessionFactory.getCurrentSession();
        Query<Role> query = session.createQuery("from Role role where role.nameRole =:nameRole", Role.class);
        query.setParameter("nameRole", nameRole);
        return query.uniqueResult();
    }

    @Override
    public List<String> getAllRoles() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select role.nameRole  FROM Role role ", String.class).list();
    }
}
