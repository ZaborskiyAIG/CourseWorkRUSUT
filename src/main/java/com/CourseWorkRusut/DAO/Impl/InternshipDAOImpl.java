package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.InternshipDAO;
import com.CourseWorkRusut.DTO.InternshipDTO;

import com.CourseWorkRusut.DTO.LearningActivitiesDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Internship;
import com.CourseWorkRusut.model.LearningActivities;
import com.CourseWorkRusut.model.PlacePractice;
import com.CourseWorkRusut.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InternshipDAOImpl implements InternshipDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public InternshipDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory =sessionFactory;

    }

    @Override
    @Deprecated
    public List<InternshipDTO> getAllInternships() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "select " +
                        "internship.internshipId, " +
                        "internship.internshipScientificDirector, " +
                        "internship.semester.numberSemester, " +
                        "internship.teacher.name, " +
                        "internship.teacher.surname, " +
                        "internship.teacher.middlename, " +
                        "internship.embeddableLearningInternship.mark, " +
                        "internship.semester.student.userId, " +
                        "internship.placePractice.placePracticeId, " +
                        "internship.embeddableLearningInternship.topic " +
                        "From Internship internship ")
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {

                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {

                     String teacher =  objects[3]+" "+objects[4]+" "+objects[5];

                        InternshipDTO internshipDTO = new InternshipDTO(
                                (Long)objects[0],
                                (String)objects[1],
                                (String)objects[2],
                                teacher,
                                (String)objects[6],
                                (Long) objects[7],
                                (Long) objects[8],
                                (String) objects[9]

                        );
                        return internshipDTO;
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                });


        return query.getResultList();
    }

    @Override
    public void delete(Internship internship) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(internship);
    }

    @Override
    public void save(Internship internship) {
        Session session = sessionFactory.getCurrentSession();
        session.save(internship);
    }

    @Override
    public void save(PlacePractice placePractice) {
        Session session = sessionFactory.getCurrentSession();
        session.save(placePractice);
    }

    @Override
    public List<PlacePractice> getAllPlace(String offset) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<PlacePractice> query = session.createQuery("select place  from PlacePractice place ",PlacePractice.class );

        query.setFirstResult(Integer.valueOf(offset));

        int quantityUsersForPagination = 25;
        query.setMaxResults(Integer.valueOf(offset)+quantityUsersForPagination);
        return query.list();
    }

    @Override
    public List<PlacePractice> getAllPlace() {
        Session session = this.sessionFactory.getCurrentSession();
        Query<PlacePractice> query = session.createQuery("select place  from PlacePractice place ",PlacePractice.class );
        return query.list();
    }

    @Override
    public PlacePractice getPlace(Long id) {
        Session session = sessionFactory.getCurrentSession();
       return session.get(PlacePractice.class, id);
    }

    @Override
    public void updatePlace(PlacePractice placePractice) {
        Session session = sessionFactory.getCurrentSession();
        session.update(placePractice);
    }

    @Override
    public void deletePlace(PlacePractice placePractice) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(placePractice);
    }

    public Long counterPlace() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count (place.placePracticeId) from PlacePractice place ");

        return (Long) query.uniqueResult();
    }

    @Override
    @Deprecated
    public List<InternshipDTO> getInternshipsByStudent(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "select " +
                        "internship.internshipId, " +
                        "internship.internshipScientificDirector, " +
                        "internship.semester.numberSemester, " +
                        "internship.teacher.name, " +
                        "internship.teacher.surname, " +
                        "internship.teacher.middlename, " +
                        "internship.embeddableLearningInternship.mark, " +
                        "internship.semester.student.userId, " +
                        "internship.placePractice.placePracticeId, " +
                        "internship.embeddableLearningInternship.topic " +
                        "From Internship internship where internship.semester.student.userId =:id")
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {

                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {

                        String teacher =  objects[3]+" "+objects[4]+" "+objects[5];

                        InternshipDTO internshipDTO = new InternshipDTO(
                                (Long)objects[0],
                                (String)objects[1],
                                (String)objects[2],
                                teacher,
                                (String)objects[6],
                                (Long) objects[7],
                                (Long) objects[8],
                                (String) objects[9]

                        );
                        return internshipDTO;
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                });

        query.setParameter("id",id);

        return query.list();
    }

    @Override
    @Deprecated
    public List<LearningActivitiesDTO> getLearningsByStudent(Long id) {
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
                        "learningActivities.embeddableLearningInternship.topic   From LearningActivities learningActivities where learningActivities..semester.student.userId =:id")
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

        query.setParameter("id",id);

        return query.list();
    }

    @Override
    public Internship getInternshipsById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Internship.class,id);
    }

    @Override
    public LearningActivities getLearningById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(LearningActivities.class,id);
    }

}
