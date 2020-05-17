package com.CourseWorkRusut.service;

import com.CourseWorkRusut.DTO.UserCounterDTO;
import com.CourseWorkRusut.DTO.UserDTO;

import com.CourseWorkRusut.model.User;

import java.util.List;
import java.util.Map;


public interface UserService {

    void register(User user);

    UserDTO update(UserDTO userDTO);

    void update(User user);

    User getUserByLogin(String login);  //подгружать только юзера и его роль

    UserDTO getUserById(Long id);

    User getUserrById(Long id);

    UserDTO getUserByLog(String login);

    User getUserByEmail(String email); //подумать можно ли вернуть тру/фолс если есть такой емэйл

    void delete(Long id);

///    Long contUsers(String nameRole);

    UserCounterDTO getAllUser(String offset);

    UserCounterDTO searchUsers(String search);


}
