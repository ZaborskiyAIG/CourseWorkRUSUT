package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.TeacherDAO;
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

        Query<User> query = session.createQuery("select user  from User user join fetch user.positions pos join fetch user.scienceDegrees sci where (type(user) in :types) and (:position is null or pos.namePosition = :position) and (:degree is null or sci.nameScienceDegree = :degree)",User.class );

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
        Query query = session.createQuery("select count(*) from User user  where user.userId in (select user.userId from User user join user.positions pos join user.scienceDegrees sci where (type(user) in :types) and (:position is null or pos.namePosition = :position) and (:degree is null or sci.nameScienceDegree = :degree) )" );
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

}
