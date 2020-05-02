package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.LearningActivitiesDAO;
import com.CourseWorkRusut.DTO.LearningActivitiesDTO;
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
    public List<LearningActivitiesDTO> getAllLearningActivities() {
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
                        "learningActivities.semester.student.userId  From LearningActivities learningActivities ")
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
                                (Long)objects[7]
                        );
                        return learningActivitiesDTO;
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                });


        return query.getResultList();
    }
}
