package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.UserDAO;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import javax.persistence.Query;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void register(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public void update(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }


    @Override
    public User getUserByLogin(String login) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where login=:login");
        query.setParameter("login", login);

       return (User) query.uniqueResult();
    }

    @Override
    public User getUserById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(User.class,id);
    }

    @Override
    public List<User> getAllUser(String offset) {
        Session session = this.sessionFactory.getCurrentSession();

       // Query query = session.createQuery("from User user", User.class);
       // Query countQuery = session.createQuery("Select count (user.userId) from User user");
       // Long countResults = (Long) countQuery.uniqueResult();  //10 - размер страницы
      //  System.out.println(countResults);
        //int lastPageNumber = (int) (Math.ceil(countResults / 10.0));
      //  System.out.println(lastPageNumber);

        Query<User> selectQuery = session.createQuery("From User", User.class);
        selectQuery.setFirstResult(Integer.valueOf(offset));
        selectQuery.setMaxResults(Integer.valueOf(offset)+25);
        //List<User> lastPage = selectQuery.list();

        return selectQuery.list();
    }

    @Override
    public Long contUsers(){
        Session session = this.sessionFactory.getCurrentSession();
       // Query countQuery = session.createQuery("Select count (user.userId) from User user");
      return (Long) session.createQuery("Select count (user.userId) from User user").uniqueResult();
    }



    public List<User> getUsersByType(String forNameClass) throws ClassNotFoundException {

        Session session = this.sessionFactory.getCurrentSession();
         Query<User> query =  session.createQuery("from User user where type(user) in :types",User.class);
         query.setParameter("types", Class.forName(forNameClass));
         return query.list();
    }

}
