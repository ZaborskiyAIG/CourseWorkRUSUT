package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.UserDAO;
import com.CourseWorkRusut.DTO.StudentDTO;
import com.CourseWorkRusut.DTO.TeacherDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.mappers.UserMapper;
import com.CourseWorkRusut.model.*;
import com.CourseWorkRusut.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserDAO userDAO ;

    private UserMapper userMapper;

    @Autowired
    StudentService studentService;

    @Autowired
    RoleService roleService;

    private TeacherService teacherService;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserDAO userDAO,  UserMapper userMapper, TeacherService teacherService){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDAO = userDAO;
        this.userMapper = userMapper;
        this.teacherService = teacherService;
    }

    @Override
    @Transactional
    public void register(User user) {
        Role role = roleService.getRoleByByName("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(role);
        userDAO.save(user);
    }


    @Override
    @Transactional
    public User update(UserDTO userDTO) {   //что происходит, когда из одного метода помеченым @транзакция вызывают другой метод c @транзакция

        User user = userDAO.getUserById(userDTO.getUserId());

        User modifiedUser =  userMapper.userDTOToUser(userDTO);
        modifiedUser.setLogin(user.getLogin());
        modifiedUser.setPassword(user.getPassword());

        if(user.getClass()==User.class){
            userDAO.delete(user);
        }

        if(userDTO.getClass() == StudentDTO.class) {
          modifiedUser = studentService.updateStudent((Student) user);
        }

        if(userDTO.getClass() == TeacherDTO.class) {
            modifiedUser = teacherService.updateTeacher((Teacher) user);
        }

        userDAO.save(modifiedUser);
        return modifiedUser;
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
        return userDAO.getAllUser(offset);
    }


    @Override
    @Transactional
    public Long contUsers() {
        return userDAO.contUsers();
    }
}
