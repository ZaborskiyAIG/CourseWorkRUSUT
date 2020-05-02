package com.CourseWorkRusut.DAO.Impl;

import com.CourseWorkRusut.DAO.InternshipDAO;
import com.CourseWorkRusut.DTO.InternshipDTO;
import com.CourseWorkRusut.model.Teacher;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
}
