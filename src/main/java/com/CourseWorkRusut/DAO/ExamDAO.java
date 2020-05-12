package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.DTO.ExamGroupDTO;
import com.CourseWorkRusut.DTO.StudentExamDTO;
import com.CourseWorkRusut.model.Exam;

import java.util.List;

public interface ExamDAO {

    void save(Exam exam);

  //  ExamGroupDTO getExamGroup(Long teacherId, String group);

    List<String> getSubjectByGroupAndTeacher(Long teacherId, String group);

    String getTypeExamByGroupAndTeacher(Long teacherId, String group, String subject, String semester );

    String getHoursExamByGroupAndTeacher(Long teacherId, String group, String subject, String semester );

    void update(Exam exam);

    List<Exam> getExamBySubjectTeacherGroup(String subject, Long id, String group, String semester);

    List<String> getSemesterByGroup(Long id, String group);

    List<StudentExamDTO> getStudentExamDTO (Long teacherId, String numberGroup, String subject, String semester);

}
