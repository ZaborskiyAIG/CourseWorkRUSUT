package com.CourseWorkRusut.DAO.Impl;


import com.CourseWorkRusut.DAO.StudyGroupDAO;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.StudyGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudyGroupDAOImpl implements StudyGroupDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long addStudyGroup(StudyGroup studyGroup) {
        Session session = sessionFactory.getCurrentSession();

       return (long) session.save(studyGroup);

    }

    @Override
    public List getAllStudyGroupBySpecialty(long specialtyId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from StudyGroup where speciality_id=:speciality_id"); //потестить запросы на User user или тупо User
        query.setParameter("speciality_id", specialtyId);
        return query.list();

    }

    @Override
    public long getCountStudentsInGroup(StudyGroup studyGroup){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(student.studyGroup) from Student student where student.studyGroup=:studyGroup", Student.class); //посмотреть че вообще за класс
        query.setParameter("studyGroup", studyGroup);
        return (long) query.getSingleResult(); //попробовать сделать return на прямую
    }

    @Override
    public StudyGroup getStudyGroupById(long id) {
        Session session = sessionFactory.getCurrentSession();
         return session.get(StudyGroup.class,id);
    }

}
