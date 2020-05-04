package com.CourseWorkRusut.service;

import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.StudyGroup;
import com.CourseWorkRusut.model.User;

import java.util.List;

public interface StudentService {

    User updateStudent(Student student);

    List<UserDTO> getStudentsByParameters(String offset, String group, String specialty);

    List<UserDTO> searchStudentByFullName(String search);

}
