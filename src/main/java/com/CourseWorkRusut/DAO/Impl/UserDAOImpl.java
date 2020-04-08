package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.UserDAO;
import com.CourseWorkRusut.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void register(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public User getUserByLogin(String login) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where login=:login");
        query.setParameter("login", login);
        User user = (User) query.uniqueResult();

       return (User) query.uniqueResult();
    }
}
