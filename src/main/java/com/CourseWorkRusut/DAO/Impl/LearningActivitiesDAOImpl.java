package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.LearningActivitiesDAO;
import com.CourseWorkRusut.DTO.LearningActivitiesDTO;
import com.CourseWorkRusut.model.LearningActivities;
import com.CourseWorkRusut.model.LearningActivitiesType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
                        "learningActivities.embeddableLearningInternship.topic   From LearningActivities learningActivities ")
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {

                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {

                        String teacher =  objects[3]+" "+objects[4]+" "+objects[5];

                        LearningActivitiesDTO learningActivitiesDTO = new LearningActivitiesDTO(
                                (Long)objects[0],
                                (String)objects[1],
                                (String)objects[2],
                                teacher,
                                (String)objects[6],
                                (Long)objects[7],
                                (String) objects[8]
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
        Query query = session.createQuery(" from LearningActivitiesType l where l.learningActivitiesType.nameType=:typ" );
        query.setParameter("typ",type);
        return (LearningActivitiesType) query.uniqueResult();
    }

    @Override
    public Long counterLearning() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count (learning.learningId) from LearningActivities learning ", );

        return (Long) query.uniqueResult();
    }
}
