package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.model.User;

import java.util.List;

public interface TeacherDAO {

    List<User> getTeachersByParameters(String offset, String position, String degree);
}
