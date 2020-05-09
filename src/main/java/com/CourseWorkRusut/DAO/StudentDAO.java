package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.DTO.StudentDTO;
import com.CourseWorkRusut.DTO.UserDTO;

import java.util.List;

public interface StudentDAO {

    List<UserDTO> searchStudentByFullName(String search);

    List<UserDTO> getStudentsByParameters(String offset, String group, String specialty);

    Long counterStudentsByFullName(String search);

    Long counterStudentsByParameters(String group, String specialty);

    List<StudentDTO> getStudentsByNumberGroup(String numberGroup);
}
