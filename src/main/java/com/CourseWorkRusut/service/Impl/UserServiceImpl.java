package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.UserDAO;

import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.mappers.UserMapper;
import com.CourseWorkRusut.model.User;
import com.CourseWorkRusut.service.StudyGroupService;
import com.CourseWorkRusut.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder; //почему оно связалось без бина?

    private UserDAO userDAO ;

    private StudyGroupService studyGroupService;

    private UserMapper userMapper;


    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserDAO userDAO, StudyGroupService studyGroupService, UserMapper userMapper){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDAO = userDAO;
        this.studyGroupService = studyGroupService;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public void register(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDAO.register(user);
    }


    @Override
    @Transactional
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    @Transactional
    public void updateUsers(List<User> users) {
        for (User user:users) {
            userDAO.update(user);
        }
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);

    }

    @Override
    @Transactional
    public UserDTO getUserById(Long id) {
        User user = userDAO.getUserById(id);
        return userMapper.userToUserDTO(user);

    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    @Transactional
    public List<UserDTO> getAllUser(String offset) {
        List<UserDTO> users =  userDAO.getAllUser(offset);

      //  List<UserDTO> userDTOS = new ArrayList<>();

      //  for(User user : users){
      //      userDTOS.add(userMapper.userToUserDTO(user));
      //  }
        return users;
    }

    @Override
    @Transactional
    public List<UserDTO> getStudentsByParameters(String offset, Long groupId, Long specialtyId) {
        List<UserDTO> users = userDAO.getStudentsByParameters(offset, groupId, specialtyId);

       // List<UserDTO> userDTOS = new ArrayList<>();

       // for (User user : users) {
       //     userDTOS.add(userMapper.userToUserDTO(user));
       // }
        return users;
    }

    @Override
    @Transactional
    public List<UserDTO> getTeachersByParameters(String offset) {
        List<User> users = userDAO.getTeachersByParameters(offset);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            userDTOS.add(userMapper.userToUserDTO(user));
        }
        return userDTOS;
    }

    @Override
    @Transactional
    public Long contUsers() {
        return userDAO.contUsers();
    }
}
