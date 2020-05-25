package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.TeacherDAO;
import com.CourseWorkRusut.DTO.TeacherNameDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.*;

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

        Query<User> query = session.createQuery("select user  from User user left join fetch user.positions pos left join fetch user.scienceDegrees sci where (type(user) in :types) and (:position is null or pos.namePosition = :position) and (:degree is null or sci.nameScienceDegree = :degree)",User.class );

        query.setParameter("position",position);
        query.setParameter("degree",degree);
        query.setParameter("types", Teacher.class);
        query.setFirstResult(Integer.valueOf(offset));

        int quantityUsersForPagination = 25;
        query.setMaxResults(Integer.valueOf(offset)+quantityUsersForPagination);
        return query.list();
    }

    @Override
    public Long counterTeachersByParameters(String position, String degree) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from User user  where user.userId in (select user.userId from User user left join user.positions pos left join user.scienceDegrees sci where (type(user) in :types) and (:position is null or pos.namePosition = :position) and (:degree is null or sci.nameScienceDegree = :degree) )" );
        query.setParameter("position",position);
        query.setParameter("degree",degree);
        query.setParameter("types", Teacher.class);

        return (Long) query.uniqueResult();
    }

    @Override
    public void saveSubjectTeacherGroup(SubjectTeacherGroup subjectTeacherGroup) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(subjectTeacherGroup);
    }

    @Override
    public void deleteSubjectTeacherGroup(SubjectTeacherGroup subjectTeacherGroup) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(subjectTeacherGroup);
    }

    @Override
    public List<SubjectTeacherGroup> getSubjectTeacherGroupByNumberGroupBySubject(List<String> numberGroup, String subject) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<SubjectTeacherGroup> query = session.createQuery("  from SubjectTeacherGroup stg  where  ( stg.studyGroup.numberGroup in (:numberGroup) ) and ( stg.subject.nameSubject = :subject)",SubjectTeacherGroup.class );

        query.setParameter("numberGroup",numberGroup);
        query.setParameter("subject",subject);


        return  query.list();
    }

    public List<SubjectTeacherGroup> getSTGByTeacherId(Long id, Long subId, Long groupId){
        Session session = this.sessionFactory.getCurrentSession();

        Query<SubjectTeacherGroup> query = session.createQuery("  from SubjectTeacherGroup stg  where  (stg.teacher.userId = :id) and (stg.subject.subjectId = :subId) and  (stg.studyGroup.groupId = :groupId) ",SubjectTeacherGroup.class );
        query.setParameter("id",id);
        query.setParameter("subId",subId);
        query.setParameter("groupId",groupId);

        return  query.list();
    }

    @Override
    public List<String> getNumberGroupByTeacherId(Long id) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<String> query = session.createQuery("select distinct stg.studyGroup.numberGroup from SubjectTeacherGroup stg  where  (stg.teacher.userId = :id) ",String.class );
        query.setParameter("id",id);

        return  query.list();
    }

    @Override
    public List<TeacherNameDTO> getFullNameTeachers() {
        Session session = this.sessionFactory.getCurrentSession();
        Query<TeacherNameDTO> query = session.createQuery(" select new com.CourseWorkRusut.DTO.TeacherNameDTO(tech.name, tech.surname, tech.middlename, tech.userId) From Teacher tech ", TeacherNameDTO.class);

        return query.list();
    }

    @Override
    public Long counterTeacherByFullName(String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(" select count (user.userId) from User user where (type(user) in :types) and " +
                "((concat(user.name,' ',user.surname,' ', IFNULL(user.middlename,'') ) LIKE :words) or " +
                "(concat(user.name,' ', IFNULL(user.middlename,''),' ',user.surname) LIKE :words) or " +
                "(concat(user.surname,' ',IFNULL(user.middlename,''),' ',user.name) LIKE :words) or" +
                "(concat(user.surname,' ',user.name,' ',IFNULL(user.middlename,'')) LIKE :words) or" +
                "(concat(IFNULL(user.middlename,''),' ',user.name,' ',user.surname) LIKE :words) or" +
                "(concat(IFNULL(user.middlename,''),' ',user.surname,' ',user.name) LIKE :words) )");
        query.setParameter("words","%"+search+"%");
        query.setParameter("types", Teacher.class);

        return (Long) query.uniqueResult();
    }

    @Override
    public List<User> searchTeacherByFullName(String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(" select user  from User user left join fetch user.positions pos left join fetch user.scienceDegrees sci " +
                "where (type(user) in :types) and (:position is null or pos.namePosition = :position) and (:degree is null or sci.nameScienceDegree = :degree) and " +
                "((concat(user.name,' ',user.surname,' ', IFNULL(user.middlename,'') ) LIKE :words) or " +
                "(concat(user.name,' ', IFNULL(user.middlename,''),' ',user.surname) LIKE :words) or " +
                "(concat(user.surname,' ',IFNULL(user.middlename,''),' ',user.name) LIKE :words) or" +
                "(concat(user.surname,' ',user.name,' ',IFNULL(user.middlename,'')) LIKE :words) or" +
                "(concat(IFNULL(user.middlename,''),' ',user.name,' ',user.surname) LIKE :words) or" +
                "(concat(IFNULL(user.middlename,''),' ',user.surname,' ',user.name) LIKE :words) )");
        query.setParameter("words","%"+search+"%");
        query.setParameter("types", Teacher.class);

        return query.list();
    }

}
