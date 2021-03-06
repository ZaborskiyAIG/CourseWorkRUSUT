package com.CourseWorkRusut.dao;

import com.CourseWorkRusut.dto.StudentExamDTO;
import com.CourseWorkRusut.dto.StudentExamsDTO;
import com.CourseWorkRusut.model.Exam;
import com.CourseWorkRusut.model.Semester;

import java.util.List;

public interface ExamDAO {

    void save(Exam exam);

    List<String> getSubjectByGroupAndTeacher(Long teacherId, String group, String semester);

    String getTypeExamByGroupAndTeacher(Long teacherId, String group, String subject, String semester );

    String getHoursExamByGroupAndTeacher(Long teacherId, String group, String subject, String semester );

    void update(Exam exam);

    List<Exam> getExamBySubjectTeacherGroup(String subject, Long id, String group, String semester);

    List<String> getSemesterByGroup(Long id, String group);

    List<StudentExamDTO> getStudentExamDTO (Long teacherId, String numberGroup, String subject, String semester);

    List<StudentExamsDTO> getStudentExams(Long id, String semester);

    Semester getSemesterByIdStudentAndNumber(Long id, String semester);

}
