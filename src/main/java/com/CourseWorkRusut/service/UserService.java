package com.CourseWorkRusut.service;

import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    void register(User user);

    void update(User user);

    void updateUsers(List<User> users);

    User getUserByLogin(String login);

    User getUserById(Long id);

    User getUserByEmail(String email);

    List<Map<String,String>> getAllUser(String offset);

    List<Map<String, String>> getStudentsByParameters(String offset, Long groupId, Long specialtyId);

    Long contUsers();

}
