package com.CourseWorkRusut.service;

import com.CourseWorkRusut.DTO.StudentDTO;
import com.CourseWorkRusut.DTO.StudentExamDTO;
import com.CourseWorkRusut.DTO.UserCounterDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Semester;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.StudyGroup;
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
