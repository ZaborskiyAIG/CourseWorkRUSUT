package com.CourseWorkRusut.dao;

import com.CourseWorkRusut.dto.StudentExamDTO;
import com.CourseWorkRusut.dto.UserDTO;
import com.CourseWorkRusut.model.Semester;

import java.util.List;

public interface StudentDAO {

    List<UserDTO> searchStudentByFullName(String search);

    List<UserDTO> getStudentsByParameters(String offset, String group, String specialty);

    Long counterStudentsByFullName(String search);

    Long counterStudentsByParameters(String group, String specialty);

    List<StudentExamDTO> getStudentsByNumberGroup(String numberGroup);

    void save(Semester semester);

    List<Semester> getSemesterByUserAndAmountSemester(Long userId, List<String> amountSemester);

    List<String> getSemesterByExam(Long teacherId,String nameSubject);

    List<StudentExamDTO> getStudentsByNumberGroupAndSubject(String numberGroup, String subject);

}
