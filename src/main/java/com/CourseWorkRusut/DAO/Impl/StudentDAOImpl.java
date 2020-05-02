package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.StudentDAO;
import com.CourseWorkRusut.DTO.UserDTO;
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
    public List<UserDTO> searchStudentByFullName(String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(" select new com.CourseWorkRusut.DTO.StudentDTO(user.userId, " +
                                                                                            "user.name, " +
                                                                                            "user.surname, " +
                                                                                            "user.middlename, " +
                                                                                            "user.email, " +
                                                                                            "user.numberBook, " +
                                                                                            "user.studyGroup.numberGroup, " +
                                                                                            "user.studyGroup.specialty.nameSpecialty, " +
                                                                                            "user.entryDate, " +
                                                                                            "user.role.nameRole) " +
                "from User user join fetch user.role where (type(user) in :types) and " +
                "((concat(user.name,' ',user.surname,' ', IFNULL(user.middlename,'') ) LIKE :words) or " +
                "(concat(user.name,' ', IFNULL(user.middlename,''),' ',user.surname) LIKE :words) or " +
                "(concat(user.surname,' ',IFNULL(user.middlename,''),' ',user.name) LIKE :words) or" +
                "(concat(user.surname,' ',user.name,' ',IFNULL(user.middlename,'')) LIKE :words) or" +
                "(concat(IFNULL(user.middlename,''),' ',user.name,' ',user.surname) LIKE :words) or" +
                "(concat(IFNULL(user.middlename,''),' ',user.surname,' ',user.name) LIKE :words) )");
        query.setParameter("words","%"+search+"%");
        query.setParameter("types", Student.class);

        return query.list();
    }
}