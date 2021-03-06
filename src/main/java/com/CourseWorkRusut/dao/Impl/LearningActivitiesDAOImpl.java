package com.CourseWorkRusut.dao.Impl;

import com.CourseWorkRusut.dao.LearningActivitiesDAO;
import com.CourseWorkRusut.dto.LearningActivitiesDTO;
import com.CourseWorkRusut.model.LearningActivities;
import com.CourseWorkRusut.model.LearningActivitiesType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LearningActivitiesDAOImpl implements LearningActivitiesDAO {

    private SessionFactory sessionFactory;

    public LearningActivitiesDAOImpl(SessionFactory  sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Deprecated
    public List<LearningActivitiesDTO> getAllLearningActivities(String offset) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "select " +
                        "learningActivities.learningId," +
                        "learningActivities.learningActivitiesType.nameType," +
                        "learningActivities.semester.numberSemester," +
                        "learningActivities.teacher.name," +
                        "learningActivities.teacher.surname," +
                        "learningActivities.teacher.middlename," +
                        "learningActivities.embeddableLearningInternship.mark," +
                        "learningActivities.semester.student.userId, " +
                        "learningActivities.embeddableLearningInternship.topic, learningActivities.semester.student.name, learningActivities.semester.student.surname, learningActivities.semester.student.middlename From LearningActivities learningActivities ")
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {

                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {

                        String teacher =  objects[4]+" "+objects[3]+" "+objects[5];

                        String student =  objects[10]+" "+objects[9]+" "+objects[11];

                        LearningActivitiesDTO learningActivitiesDTO = new LearningActivitiesDTO(
                                (Long)objects[0],
                                (String)objects[1],
                                (String)objects[2],
                                teacher,
                                (String)objects[6],
                                (Long)objects[7],
                                (String) objects[8],
                                student
                        );
                        return learningActivitiesDTO;
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                });
        query.setFirstResult(Integer.valueOf(offset));

        int quantityUsersForPagination = 25;
        query.setMaxResults(Integer.valueOf(offset)+quantityUsersForPagination);


        return query.getResultList();
    }

    @Override
    public void delete(LearningActivities learningActivities) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(learningActivities);
    }

    @Override
    public List<String> getTypeLearning() {
        Session session = this.sessionFactory.getCurrentSession();
        Query<String> query = session.createQuery("select l.nameType from LearningActivitiesType l", String.class );
        return   query.list();
    }

    @Override
    public LearningActivitiesType getLearningByType(String type) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<LearningActivitiesType> query = session.createQuery(" from LearningActivitiesType l where l.nameType=:typ", LearningActivitiesType.class );
        query.setParameter("typ",type);
        return  query.uniqueResult();
    }

    @Override
    public Long counterLearning() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count (learning.learningId) from LearningActivities learning ");

        return (Long) query.uniqueResult();
    }

    @Override
    public void save(LearningActivities learningActivities) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(learningActivities);
    }

    @Override
    @Deprecated
    public List<LearningActivitiesDTO> getLearningActivitiesBySearch(String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "select " +
                        "learningActivities.learningId," +
                        "learningActivities.learningActivitiesType.nameType," +
                        "learningActivities.semester.numberSemester," +
                        "learningActivities.teacher.name," +
                        "learningActivities.teacher.surname," +
                        "learningActivities.teacher.middlename," +
                        "learningActivities.embeddableLearningInternship.mark," +
                        "learningActivities.semester.student.userId, " +
                        "learningActivities.embeddableLearningInternship.topic, learningActivities.semester.student.name, learningActivities.semester.student.surname, learningActivities.semester.student.middlename From LearningActivities learningActivities where learningActivities.embeddableLearningInternship.topic LIKE : search")
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {

                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {

                        String teacher =  objects[4]+" "+objects[3]+" "+objects[5];


                        String student =  objects[10]+" "+objects[9]+" "+objects[11];

                        LearningActivitiesDTO learningActivitiesDTO = new LearningActivitiesDTO(
                                (Long)objects[0],
                                (String)objects[1],
                                (String)objects[2],
                                teacher,
                                (String)objects[6],
                                (Long)objects[7],
                                (String) objects[8],
                                student
                        );
                        return learningActivitiesDTO;
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                });


        query.setParameter("search", "%"+search+"%");

        return query.getResultList();
    }

    @Override
    public Long counterLearningBySearch(String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count (learning.learningId) from LearningActivities learning where learning.embeddableLearningInternship.topic LIKE : search ");
        query.setParameter("search", "%"+search+"%");

        return (Long) query.uniqueResult();
    }
}
