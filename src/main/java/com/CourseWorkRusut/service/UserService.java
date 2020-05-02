package com.CourseWorkRusut.service;

import com.CourseWorkRusut.DTO.UserDTO;

import com.CourseWorkRusut.model.User;

import java.util.List;


public interface UserService {

    void register(User user);

    UserDTO update(UserDTO userDTO);

    User getUserByLogin(String login);  //подгружать только юзера и его роль

    UserDTO getUserById(Long id);

    User getUserByEmail(String email); //подумать можно ли вернуть тру/фолс если есть такой емэйл

    void delete(User user);

    Long contUsers(String nameRole);

    List<UserDTO> getAllUser(String offset);

    List<UserDTO> searchUsers(String search);

}
