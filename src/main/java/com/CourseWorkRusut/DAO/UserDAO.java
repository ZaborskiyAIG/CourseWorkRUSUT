package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.model.User;

public interface UserDAO {

    void register(User user);

    User getUserByLogin(String login);

}
