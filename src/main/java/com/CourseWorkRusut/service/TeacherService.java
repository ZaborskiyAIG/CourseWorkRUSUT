package com.CourseWorkRusut.service;

import com.CourseWorkRusut.DTO.UserCounterDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Teacher;
import com.CourseWorkRusut.model.User;

import java.util.List;

public interface TeacherService {

    User updateTeacher(Teacher teacher);

    UserCounterDTO getTeachersByParameters(String offset, String position, String degree);

}
