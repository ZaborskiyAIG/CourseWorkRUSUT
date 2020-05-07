package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.SubjectDAO;
import com.CourseWorkRusut.model.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectDAOImpl implements SubjectDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public SubjectDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Subject> getSubjectByTeacher(Long idTeacher) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<Subject> query = session.createQuery("select subjectTeacherGroup.subject  from SubjectTeacherGroup subjectTeacherGroup  where  subjectTeacherGroup.teacher.userId = :idTeacher",Subject.class );

        query.setParameter("idTeacher",idTeacher);

        return  query.list();
    }

    @Override
    public Subject getSubjectByName(String nameSubject) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Subject subject where subject.nameSubject=:nameSubject");
        query.setParameter("nameSubject", nameSubject);

        return (Subject) query.uniqueResult();
    }

}
