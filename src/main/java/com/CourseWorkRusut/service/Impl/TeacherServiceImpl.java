package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.PositionScienceDegreeDAO;
import com.CourseWorkRusut.DAO.TeacherDAO;
import com.CourseWorkRusut.DAO.UserDAO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.mappers.UserMapper;
import com.CourseWorkRusut.model.Position;
import com.CourseWorkRusut.model.ScienceDegree;
import com.CourseWorkRusut.model.Teacher;
import com.CourseWorkRusut.model.User;
import com.CourseWorkRusut.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private PositionScienceDegreeDAO positionScienceDegreeService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDAO userDAO;

    @Autowired
    TeacherDAO teacherDAO;

    @Autowired
    public TeacherServiceImpl(PositionScienceDegreeDAO positionScienceDegreeService){
        this.positionScienceDegreeService =  positionScienceDegreeService;
    }

    @Transactional
    public User updateTeacher(Teacher teacher){

        List<String> namePositions = new ArrayList<>();
        for(Position position : teacher.getPositions()){
            namePositions.add(position.getNamePosition());
        }

        List<String> nameScienceDegrees = new ArrayList<>();
        for(ScienceDegree scienceDegree : teacher.getScienceDegrees()){
            nameScienceDegrees.add(scienceDegree.getNameScienceDegree());
        }

        teacher.setPositions(positionScienceDegreeService.getPositionsByByName(namePositions));
        teacher.setScienceDegrees(positionScienceDegreeService.getScienceDegreeByByName(nameScienceDegrees));

        return teacher;
    }

    @Override
    @Transactional
    public List<UserDTO> getTeachersByParameters(String offset) {
        List<User> users = teacherDAO.getTeachersByParameters(offset);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            userDTOS.add(userMapper.userToUserDTO(user));
        }
        return userDTOS;
    }




}
