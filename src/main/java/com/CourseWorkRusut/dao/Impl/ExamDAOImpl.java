package com.CourseWorkRusut.dao.Impl;

import com.CourseWorkRusut.dao.ExamDAO;
import com.CourseWorkRusut.dto.StudentExamDTO;
import com.CourseWorkRusut.dto.StudentExamsDTO;
import com.CourseWorkRusut.model.Exam;
import com.CourseWorkRusut.model.Semester;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExamDAOImpl implements ExamDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public ExamDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Exam exam) {
        Session session = sessionFactory.getCurrentSession();
        session.save(exam);
    }

    @Override
    public List<String> getSubjectByGroupAndTeacher(Long teacherId, String group, String semester) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<String> query = session.createQuery("select distinct exam.subject.nameSubject from Exam exam where exam.subject.nameSubject in (select stg.subject.nameSubject from SubjectTeacherGroup stg where stg.teacher.userId =:teacherId and stg.studyGroup.numberGroup =:numberGroup) and (exam.markExam is not null) and exam.teacher.userId =:teacherId and exam.semester.numberSemester =:semester and exam.semester.student.studyGroup.numberGroup =:numberGroup", String.class);
        query.setParameter("teacherId",teacherId);
        query.setParameter("numberGroup",group);
        query.setParameter("semester",semester);

        return  query.list();
    }

    @Override
    public String getTypeExamByGroupAndTeacher(Long teacherId, String group, String subject, String semester) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(" select exam.typeExam from Exam exam where exam.teacher.userId =:teacherId and exam.semester.student.studyGroup.numberGroup =:numberGroup and exam.subject.nameSubject =:subject and exam.semester.numberSemester =:semester");
        query.setParameter("teacherId",teacherId);
        query.setParameter("numberGroup",group);
        query.setParameter("subject",subject);
        query.setParameter("semester",semester);
        query.setMaxResults(1);

        return (String) query.uniqueResult();
    }

    @Override
    public String getHoursExamByGroupAndTeacher(Long teacherId, String group, String subject, String semester) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(" select exam.hours from Exam exam where exam.teacher.userId =:teacherId and exam.semester.student.studyGroup.numberGroup =:numberGroup and exam.subject.nameSubject =:subject and exam.semester.numberSemester =:semester");
        query.setParameter("teacherId",teacherId);
        query.setParameter("numberGroup",group);
        query.setParameter("subject",subject);
        query.setParameter("semester",semester);
        query.setMaxResults(1);

        return (String) query.getSingleResult();
    }

    @Override
    public void update(Exam exam) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(exam);
    }

    @Override
    public List<Exam> getExamBySubjectTeacherGroup(String subject, Long id, String group, String semester) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Exam> query = session.createQuery(" from Exam exam where exam.teacher.userId =:teacherId and exam.subject.nameSubject =:subject and exam.semester.numberSemester =:semester and exam.semester.student.studyGroup.numberGroup =:numberGroup ", Exam.class  );
        query.setParameter("teacherId",id);  //
        query.setParameter("subject",subject);
        query.setParameter("semester",semester);
        query.setParameter("numberGroup",group);

        return  query.list();
    }

    @Override
    public List<String> getSemesterByGroup(Long id, String group) {   //нужен id школьника, чтобы не ловить все семестры
        Session session = this.sessionFactory.getCurrentSession();
        Query<String> query = session.createQuery("select exam.semester.numberSemester from Exam exam where exam.teacher.userId =:teacherId and exam.semester.student.studyGroup.numberGroup =:group ", String.class  );
        query.setParameter("teacherId",id);
        query.setParameter("group",group);

        return  query.list();
    }

    @Override
    public List<StudentExamDTO> getStudentExamDTO(Long teacherId, String numberGroup, String subject, String semester) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select  new com.CourseWorkRusut.dto.StudentExamDTO(exam.semester.student.userId, exam.semester.student.name, exam.semester.student.surname, exam.semester.student.middlename, exam.semester.student.numberBook, exam.markExam) from Exam exam where exam.teacher.userId =:teacherId and exam.semester.student.studyGroup.numberGroup =:group and exam.subject.nameSubject =:subject and exam.semester.numberSemester =:semester "  );
        query.setParameter("teacherId",teacherId);
        query.setParameter("group",numberGroup);
        query.setParameter("subject",subject);
        query.setParameter("semester",semester);
        return  query.list();
    }

    @Override
    public List<StudentExamsDTO> getStudentExams(Long id, String semester) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select  new com.CourseWorkRusut.dto.StudentExamsDTO(exam.subject.nameSubject, exam.semester.numberSemester, exam.markExam, exam.typeExam) from Exam exam where exam.semester.student.userId =:Id and  exam.semester.numberSemester =:semester "  );
        query.setParameter("Id",id);
        query.setParameter("semester",semester);
        return  query.list();
    }

    @Override
    public Semester getSemesterByIdStudentAndNumber(Long id, String semester) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Semester> query = session.createQuery("from Semester sem where sem.student.userId =:Id and  sem.numberSemester =:semester ", Semester.class  );
        query.setParameter("Id",id);
        query.setParameter("semester",semester);
        return  query.uniqueResult();
    }

}
