package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.UserDAO;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public User getUserByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where email=:email");
        query.setParameter("email", email);

        return (User) query.uniqueResult();
    }

    @Override
    public List<User> getAllUser(String offset) {
        int quantityUsersForPagination = 25;

        Session session = this.sessionFactory.getCurrentSession();
        Query<User> selectQuery = session.createQuery("From User", User.class);
        selectQuery.setFirstResult(Integer.valueOf(offset));
        selectQuery.setMaxResults(Integer.valueOf(offset)+quantityUsersForPagination);

        return selectQuery.list();
    }

    @Override
    public List<User> getStudentsByParameters(String offset, long groupId, long specialtyId) {
        Session session = this.sessionFactory.getCurrentSession();

//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Student> criteriaQuery =  criteriaBuilder.createQuery(Student.class);
//        Root<Student> userRoot = criteriaQuery.from(Student.class);
//        ParameterExpression<Long> p = criteriaBuilder.parameter(Long.class);
//        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get("studyGroup").get("specialty").get("specialtyId"), p));
//        Query<Student> query = session.createQuery(criteriaQuery);
//        query.setParameter(p, null);
//        List<Student> results = query.list();

        Query<User> query = session.createQuery("select user from User user where (type(user) in :types) and (:specialtyId is null or user.studyGroup.specialty.specialtyId = :specialtyId) and (:groupId is null or user.studyGroup.groupId = :groupId) ", User.class);
        query.setParameter("specialtyId", specialtyId);
        query.setParameter("groupId", groupId);
        query.setParameter("types", Student.class);
        return query.list();
      }

    @Override
    public Long contUsers(){
        Session session = this.sessionFactory.getCurrentSession();
      return (Long) session.createQuery("Select count (user.userId) from User user").uniqueResult();
    }



    public List<User> getUsersByType(String forNameClass) throws ClassNotFoundException {

        Session session = this.sessionFactory.getCurrentSession();
         Query<User> query =  session.createQuery("from User user where type(user) in :types",User.class);
         query.setParameter("types", Class.forName(forNameClass));
         return query.list();
    }

}
