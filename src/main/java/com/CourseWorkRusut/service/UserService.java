package com.CourseWorkRusut.service;

import com.CourseWorkRusut.model.User;

public interface UserService {

    void register(User user);

    User getUserByLogin(String login);

}
