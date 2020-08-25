package com.CourseWorkRusut.service;

import com.CourseWorkRusut.dto.StudentExamDTO;
import com.CourseWorkRusut.dto.UserCounterDTO;
import com.CourseWorkRusut.model.Semester;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.User;

import java.util.List;

public interface StudentService {

    User updateStudent(Student student, User user);

    UserCounterDTO getStudentsByParameters(String offset, String group, String specialty);

    UserCounterDTO searchStudentByFullName(String search);

    List<StudentExamDTO> getStudentsByNumberGroup(String numberGroup);

    List<StudentExamDTO> getStudentsByNumberGroupAndSubject(String numberGroup, String subject);

    List<Semester> getSemesterByUserAndAmountSemester(Long userId, List<String> amountSemester);

}
