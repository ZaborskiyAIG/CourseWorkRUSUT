package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.StudentDAO;
import com.CourseWorkRusut.DTO.StudentDTO;
import com.CourseWorkRusut.DTO.StudentExamDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Semester;
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
    public List<UserDTO> getStudentsByParameters(String offset, String group, String specialty) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<UserDTO> query = session.createQuery("select new com.CourseWorkRusut.DTO.StudentDTO(user.userId, " +
                "user.name, " +
                "user.surname, " +
                "user.middlename, " +
                "user.email, user.numberBook, " +
                "user.studyGroup.numberGroup, " +
                "user.studyGroup.specialty.nameSpecialty, " +
                "user.entryDate, user.role.nameRole) " +
                "from User user where (type(user) in :types) and (:specialty is null or user.studyGroup.specialty.nameSpecialty = :specialty) and (:group is null or user.studyGroup.numberGroup = :group) ", UserDTO.class);

        query.setParameter("specialty",specialty );
        query.setParameter("group",group);
        query.setParameter("types", Student.class);
        query.setFirstResult(Integer.valueOf(offset));
        int quantityUsersForPagination = 25;
        query.setMaxResults(Integer.valueOf(offset)+quantityUsersForPagination);
        return query.list();
    }

    @Override
    public Long counterStudentsByParameters(String group, String specialty) {
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery("select count (user.userId) from User user where (type(user) in :types) and (:specialty is null or user.studyGroup.specialty.nameSpecialty = :specialty) and (:group is null or user.studyGroup.numberGroup = :group) ");

        query.setParameter("specialty",specialty );
        query.setParameter("group",group);
        query.setParameter("types", Student.class);
        return (Long) query.uniqueResult();
    }

    @Override
    public List<StudentExamDTO> getStudentsByNumberGroup(String numberGroup) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(" select new com.CourseWorkRusut.DTO.StudentExamDTO(user.userId, " +
                "user.name, " +
                "user.surname, " +
                "user.middlename, " +
                "user.numberBook) " +
                "from User user where (type(user) in :types) and (user.studyGroup.numberGroup =:numberGroup ) ");
        query.setParameter("numberGroup","numberGroup");
        query.setParameter("types", Student.class);

        return query.list();
    }

    @Override
    public void save(Semester semester) {
        Session session = sessionFactory.getCurrentSession();
        session.save(semester);
    }

    @Override
    public List<Semester> getSemesterByUserAndAmountSemester(Long userId, List<String> amountSemester) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Semester semester where (semester.student.userId =:userId ) and (semester.numberSemester in (:amountSemester) ) ", Semester.class);
        query.setParameter("userId","userId");
        query.setParameterList("amountSemester", amountSemester);

        return query.list();
    }

    public Long counterStudentsByFullName(String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(" select count (user.userId) from User user where (type(user) in :types) and " +
                "((concat(user.name,' ',user.surname,' ', IFNULL(user.middlename,'') ) LIKE :words) or " +
                "(concat(user.name,' ', IFNULL(user.middlename,''),' ',user.surname) LIKE :words) or " +
                "(concat(user.surname,' ',IFNULL(user.middlename,''),' ',user.name) LIKE :words) or" +
                "(concat(user.surname,' ',user.name,' ',IFNULL(user.middlename,'')) LIKE :words) or" +
                "(concat(IFNULL(user.middlename,''),' ',user.name,' ',user.surname) LIKE :words) or" +
                "(concat(IFNULL(user.middlename,''),' ',user.surname,' ',user.name) LIKE :words) )");
        query.setParameter("words","%"+search+"%");
        query.setParameter("types", Student.class);

        return (Long) query.uniqueResult();
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
                "from User user where (type(user) in :types) and " +
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
