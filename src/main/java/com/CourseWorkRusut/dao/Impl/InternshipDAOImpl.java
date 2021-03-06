package com.CourseWorkRusut.dao.Impl;

import com.CourseWorkRusut.dao.InternshipDAO;
import com.CourseWorkRusut.dto.InternshipDTO;

import com.CourseWorkRusut.dto.LearningActivitiesDTO;
import com.CourseWorkRusut.model.Internship;
import com.CourseWorkRusut.model.LearningActivities;
import com.CourseWorkRusut.model.PlacePractice;
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
    public List<InternshipDTO> getAllInternships(String offset) {
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
                        "internship.embeddableLearningInternship.topic, internship.semester.student.name, internship.semester.student.surname, internship.semester.student.middlename  " +
                        "From Internship internship ")
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {

                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {

                        String teacher =  objects[4]+" "+objects[3]+" "+objects[5];

                        String student =  objects[11]+" "+objects[10]+" "+objects[12];

                        InternshipDTO internshipDTO = new InternshipDTO(
                                (Long)objects[0],
                                (String)objects[1],
                                (String)objects[2],
                                teacher,
                                (String)objects[6],
                                (Long) objects[7],
                                (Long) objects[8],
                                (String) objects[9],
                                student

                        );
                        return internshipDTO;
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
                        "internship.embeddableLearningInternship.topic, internship.semester.student.name, internship.semester.student.surname, internship.semester.student.middlename " +
                        "From Internship internship where internship.semester.student.userId =:id")
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {

                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {

                        String teacher =  objects[4]+" "+objects[3]+" "+objects[5];

                        String student =  objects[11]+" "+objects[10]+" "+objects[12];

                        InternshipDTO internshipDTO = new InternshipDTO(
                                (Long)objects[0],
                                (String)objects[1],
                                (String)objects[2],
                                teacher,
                                (String)objects[6],
                                (Long) objects[7],
                                (Long) objects[8],
                                (String) objects[9],
                                student

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
                        "learningActivities.embeddableLearningInternship.topic, learningActivities.semester.student.name, learningActivities.semester.student.surname, learningActivities.semester.student.middlename  From LearningActivities learningActivities where learningActivities.semester.student.userId =:id")
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

       query.setParameter("id",id);

        return query.list();
    }

    @Override
    public Internship getInternshipsById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Internship.class,id);
    }

    @Override
    public void update(Internship internship) {
        Session session = sessionFactory.getCurrentSession();
        session.update(internship);
    }

    @Override
    public void update(LearningActivities learningActivities) {
        Session session = sessionFactory.getCurrentSession();
        session.update(learningActivities);
    }

    @Override
    public LearningActivities getLearningById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(LearningActivities.class,id);
    }

    @Override
    @Deprecated
    public List<InternshipDTO> getInternshipsByTeacher(Long id) {
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
                        "internship.embeddableLearningInternship.topic, internship.semester.student.name, internship.semester.student.surname, internship.semester.student.middlename " +
                        "From Internship internship where internship.teacher.userId =:id")
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {

                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {

                        String teacher =  objects[4]+" "+objects[3]+" "+objects[5];

                        String student =  objects[11]+" "+objects[10]+" "+objects[12];


                        InternshipDTO internshipDTO = new InternshipDTO(
                                (Long)objects[0],
                                (String)objects[1],
                                (String)objects[2],
                                teacher,
                                (String)objects[6],
                                (Long) objects[7],
                                (Long) objects[8],
                                (String) objects[9],
                                student

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
    public List<LearningActivitiesDTO> getLearningsByTeacher(Long id) {
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
                        "learningActivities.embeddableLearningInternship.topic,  learningActivities.semester.student.name, learningActivities.semester.student.surname, learningActivities.semester.student.middlename  From LearningActivities learningActivities where learningActivities.teacher.userId =:id")
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

        query.setParameter("id",id);

        return query.list();
    }

    @Override
    public Long counterInternship() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count (inter.internshipId) from Internship inter ");

        return (Long) query.uniqueResult();
    }

    @Override
    @Deprecated
    public List<InternshipDTO> getInternshipsBySearch(String search) {
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
                        "internship.embeddableLearningInternship.topic, internship.semester.student.name, internship.semester.student.surname, internship.semester.student.middlename  " +
                        "From Internship internship where internship.embeddableLearningInternship.topic LIKE :search ")
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {

                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {

                        String teacher =  objects[4]+" "+objects[3]+" "+objects[5];

                        String student =  objects[11]+" "+objects[10]+" "+objects[12];

                        InternshipDTO internshipDTO = new InternshipDTO(
                                (Long)objects[0],
                                (String)objects[1],
                                (String)objects[2],
                                teacher,
                                (String)objects[6],
                                (Long) objects[7],
                                (Long) objects[8],
                                (String) objects[9],
                                student

                        );
                        return internshipDTO;
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                });
        query.setParameter("search","%"+search+"%");

        return query.getResultList();
    }

    @Override
    public Long counterInternshipBySearch(String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count (inter.internshipId) from Internship inter where inter.embeddableLearningInternship.topic LIKE :search ");

        query.setParameter("search", "%"+search+"%");
        return (Long) query.uniqueResult();
    }

    @Override
    public List<PlacePractice> getPlaceBySearch(String search) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<PlacePractice> query = session.createQuery("select place  from PlacePractice place where place.companyName LIKE :search",PlacePractice.class );

        query.setParameter("search","%"+search+"%");

        return query.list();
    }

    @Override
    public Long counterPlaceBySearch(String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count (place.placePracticeId) from PlacePractice place where place.companyName LIKE :search");

        query.setParameter("search","%"+search+"%");

        return (Long) query.uniqueResult();
    }

}
