package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.DTO.UserDTO;

import java.util.List;

public interface StudentDAO {

    List<UserDTO> searchStudentByFullName(String search);
}
