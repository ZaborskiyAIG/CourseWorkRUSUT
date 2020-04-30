package com.CourseWorkRusut.service;

import com.CourseWorkRusut.DTO.UserDTO;

import com.CourseWorkRusut.model.User;

import java.util.List;


public interface UserService {

    void register(User user);

    UserDTO update(UserDTO userDTO);

    User getUserByLogin(String login);

    void delete(User user);

    UserDTO getUserById(Long id);

    User getUserByEmail(String email);

    Long contUsers(String nameRole);

    List<UserDTO> getAllUser(String offset);


}
