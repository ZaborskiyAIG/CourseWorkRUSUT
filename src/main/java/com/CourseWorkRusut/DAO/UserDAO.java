package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.model.User;

import java.util.List;

public interface UserDAO {

    void register(User user);

    void update(User user);

    User getUserByLogin(String login);

    User getUserById(long id);

    User getUserByEmail(String email);

    List getAllUser(String offset);

    List<User> getStudentsByParameters(String offset, long groupId, long specialtyId);

    Long contUsers();

}
