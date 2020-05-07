package com.CourseWorkRusut.DAO.Impl;


import com.CourseWorkRusut.DAO.StudyGroupDAO;
import com.CourseWorkRusut.model.StudyGroup;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudyGroupDAOImpl implements StudyGroupDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public StudyGroupDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long addStudyGroup(StudyGroup studyGroup) {
        Session session = sessionFactory.getCurrentSession();
        return (long) session.save(studyGroup);
    }

    @Override
    public List<String> getAllStudyGroupByNameSpecialty(String nameSpecialty) {
        Session session = sessionFactory.getCurrentSession();
        Query<String> query = session.createQuery(" select studyGroup.numberGroup from StudyGroup studyGroup where studyGroup.specialty.nameSpecialty =:nameSpecialty", String.class);
        query.setParameter("nameSpecialty", nameSpecialty);
        return query.list();
    }

    @Override
    public Long getCountStudentsInGroup(String numberGroup){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(student.studyGroup) from Student student where student.studyGroup.numberGroup =:numberGroup"); //посмотреть че вообще за класс
        query.setParameter("numberGroup", numberGroup);
        return (Long) query.getSingleResult();
    }

    @Override
    public StudyGroup getStudyGroupById(Long id) {
        Session session = sessionFactory.getCurrentSession();
         return session.get(StudyGroup.class,id);
    }

    @Override
    public StudyGroup getStudyGroupByNumberGroup(String numberGroup) {
        Session session = sessionFactory.getCurrentSession();
        Query<StudyGroup> query = session.createQuery("from StudyGroup studyGroup where studyGroup.numberGroup = : numberGroup ",StudyGroup.class);
        query.setParameter("numberGroup", numberGroup);
        return query.uniqueResult();
    }

    @Override
    public List<StudyGroup> getStudyGroupBySubject(String nameSubject) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<StudyGroup> query = session.createQuery("select subjectTeacherGroup.studyGroup  from SubjectTeacherGroup subjectTeacherGroup  where  (subjectTeacherGroup.subject.nameSubject = :nameSubject)",StudyGroup.class );

        query.setParameter("nameSubject",nameSubject);

        return  query.list();
    }

}
