package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.UserDAO;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.User;
import com.CourseWorkRusut.service.StudyGroupService;
import com.CourseWorkRusut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder; //почему оно связалось без бина?

    private UserDAO userDAO ;

    private StudyGroupService studyGroupService;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserDAO userDAO, StudyGroupService studyGroupService){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDAO = userDAO;
        this.studyGroupService = studyGroupService;
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
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    @Transactional
    public List<Map<String,String>> getAllUser(String offset) {

        List<User> users =  userDAO.getAllUser(offset);


        List<Map<String,String>> userList = new ArrayList<>();

        for(User user : users){
            Map<String, String> userMap = new HashMap<>();
            userMap.put("userId",String.valueOf(user.getId()));
            userMap.put("name",user.getName());
            userMap.put("surname",user.getSurname());
            userMap.put("midlename",user.getMidlename());

            userMap.put("role",String.valueOf(user.getAuthorities().iterator().next()));
            userList.add(userMap);
        }
        return userList;
    }

    @Override
    @Transactional
    public List<Map<String, String>> getStudentsByParameters(String offset, Long groupId, Long specialtyId) {
        List<User> users = userDAO.getStudentsByParameters(offset, groupId, specialtyId);

        List<Map<String, String>> userList = new ArrayList<>();

        for (User user : users) {
            Map<String, String> userMap = new HashMap<>();
            userMap.put("userId", String.valueOf(user.getId()));
            userMap.put("name", user.getName());
            userMap.put("surname", user.getSurname());
            userMap.put("midlename", user.getMidlename());
            userMap.put("numberBook", String.valueOf(((Student) user).getNumberBook()));
            userMap.put("numberGroup", ((Student) user).getStudyGroup().getNumberGroup());
            userMap.put("specialtyId", String.valueOf(specialtyId));
            userMap.put("groupId", String.valueOf(groupId));
            userMap.put("role", String.valueOf(user.getAuthorities().iterator().next()));

            userList.add(userMap);

        }
        return userList;
    }

    @Override
    @Transactional
    public Long contUsers() {
        return userDAO.contUsers();
    }
}
