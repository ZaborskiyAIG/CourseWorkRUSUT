package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.User;

import java.util.List;


public interface UserDAO {

    void save(User user);

    void update(User user);

    void delete (User user);

    User getUserByLogin(String login);

    User getUserById(Long id);

    User getUserByEmail(String email);

    List<UserDTO> getAllUser(String offset);

    Long contUsers(String nameRole);

    List<UserDTO> searchUsersByWords(String words);

}
