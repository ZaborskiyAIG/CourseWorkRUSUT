package com.CourseWorkRusut.service;

import com.CourseWorkRusut.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    void register(User user);

    void update(User user);

    User getUserByLogin(String login);

    User getUserById(long id);

    List<Map<String,String>> getAllUser(String offset);

    Long contUsers();

}
