package com.CourseWorkRusut.dao;

import com.CourseWorkRusut.dto.UserDTO;
import com.CourseWorkRusut.model.User;

import java.time.LocalDate;
import java.util.List;


public interface UserDAO {

    Long save(User user);

    void update(User user);

    void delete (User user);

    User getUserByLogin(String login);

    User getUserById(Long id);

    User getUserByEmail(String email);

    List<UserDTO> getAllUser(String offset);

    Long contUsers(String nameRole);

    Long contUsersByFullName(String words);

    List<UserDTO> searchUsersByWords(String words);




}
