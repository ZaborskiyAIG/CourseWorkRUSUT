package com.CourseWorkRusut.service;

import com.CourseWorkRusut.dto.UserCounterDTO;
import com.CourseWorkRusut.dto.UserDTO;

import com.CourseWorkRusut.model.User;

import java.util.List;
import java.util.Map;


public interface UserService {

    void register(User user);

    UserDTO update(UserDTO userDTO);

    void update(User user);

    User getUserByLogin(String login);

    UserDTO getUserById(Long id);

    User getUserrById(Long id);

    UserDTO getUserByLog(String login);

    User getUserByEmail(String email);

    void delete(Long id);

    UserCounterDTO getAllUser(String offset);

    UserCounterDTO searchUsers(String search);


}
