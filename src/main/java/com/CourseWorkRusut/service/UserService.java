package com.CourseWorkRusut.service;

import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    void register(User user);

    User update(UserDTO userDTO);

    void updateUsers(List<User> users);

    User getUserByLogin(String login);

    UserDTO getUserById(Long id);

    User getUserByEmail(String email);

    Long contUsers();

    List<UserDTO> getAllUser(String offset);

    List<UserDTO> getStudentsByParameters(String offset, Long groupId, Long specialtyId);

    List<UserDTO> getTeachersByParameters(String offset);



}
