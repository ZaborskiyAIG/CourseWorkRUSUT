package com.CourseWorkRusut.service;

import com.CourseWorkRusut.DTO.UserCounterDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.StudyGroup;
import com.CourseWorkRusut.model.User;

import java.util.List;

public interface StudentService {

    User updateStudent(Student student);

    UserCounterDTO getStudentsByParameters(String offset, String group, String specialty);

    UserCounterDTO searchStudentByFullName(String search);

}
