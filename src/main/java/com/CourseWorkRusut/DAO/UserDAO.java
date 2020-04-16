package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.model.User;

import java.util.List;

public interface UserDAO {

    void register(User user);

    void update(User user);

    User getUserByLogin(String login);

    User getUserById(long id);

    List getAllUser(String offset);

    Long contUsers();

}
