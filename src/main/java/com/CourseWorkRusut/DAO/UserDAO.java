package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.model.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {

    void register(User user);

    void update(User user);

    User getUserByLogin(String login);

    User getUserById(Long id);

    User getUserByEmail(String email);

    List<User> getAllUser(String offset);

    List<User> getStudentsByParameters(String offset, Long groupId, Long specialtyId);

    List<User> getTeachersByParameters(String offset);

    Long contUsers();

}
