package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.UserDAO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.Teacher;
import com.CourseWorkRusut.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {   //save, update,merge,persist разобрать более подробно
                                                //jpa constructor, для сложных ResultTrasformer
    private SessionFactory sessionFactory;

    private final int quantityUsersForPagination = 25;


    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);

    }

    @Override
    public void update(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(user);
    }

    @Override
    public void delete(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(user);

    }


    @Override
    public User getUserByLogin(String login) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where login=:login");
        query.setParameter("login", login);

       return (User) query.uniqueResult();
    }

    @Override
    public User getUserById(Long id) {
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
    public List<UserDTO> getAllUser(String offset) {

        Session session = this.sessionFactory.getCurrentSession();
        Query<UserDTO> query = session.createQuery(" select new com.CourseWorkRusut.DTO.UserDTO(user.userId, user.name,user.surname,user.middlename,user.email, user.role.nameRole) From User user where type(user) in :types", UserDTO.class);
        query.setParameter("types", User.class);
        query.setFirstResult(Integer.valueOf(offset));
        query.setMaxResults(Integer.valueOf(offset)+quantityUsersForPagination);

        return query.list();
    }


    @Override
    public List<UserDTO> getStudentsByParameters(String offset, Long groupId, Long specialtyId) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<UserDTO> query = session.createQuery("select new com.CourseWorkRusut.DTO.StudentDTO(user.userId, user.name, user.surname, user.middlename, user.email, user.numberBook, user.studyGroup.numberGroup, user.studyGroup.specialty.nameSpecialty, user.entryDate, user.role.nameRole) from User user where (type(user) in :types) and (:specialtyId is null or user.studyGroup.specialty.specialtyId = :specialtyId) and (:groupId is null or user.studyGroup.groupId = :groupId) ", UserDTO.class);

        query.setParameter("specialtyId",specialtyId);
        query.setParameter("groupId",groupId );
        query.setParameter("types", Student.class);
        query.setFirstResult(Integer.valueOf(offset));
        query.setMaxResults(Integer.valueOf(offset)+quantityUsersForPagination);
        return query.list();
    }

    @Override
    public List<User> getTeachersByParameters(String offset) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery(" select user from User user where type(user) in :types",User.class );
        query.setParameter("types", Teacher.class);
        query.setFirstResult(Integer.valueOf(offset));
        query.setMaxResults(Integer.valueOf(offset)+quantityUsersForPagination);
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
