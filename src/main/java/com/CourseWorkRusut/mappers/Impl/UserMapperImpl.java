package com.CourseWorkRusut.mappers.Impl;

import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.mappers.StudentMapper;
import com.CourseWorkRusut.mappers.TeacherMapper;
import com.CourseWorkRusut.mappers.UserMapper;
import com.CourseWorkRusut.model.Admin;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.Teacher;
import com.CourseWorkRusut.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeacherMapper teacherMapper;



    @Override
    public UserDTO userToUserDTO(User user) {

         UserDTO userDto;

        if(user.getClass() == User.class){
            System.out.println("Us"+user.getUserId());
            userDto = new UserDTO();
            userDto.setName(user.getName());
            userDto.setMidlename(user.getMidlename());
            userDto.setSurname(user.getSurname());
            userDto.setEmail(user.getEmail());
            userDto.setRole(user.getRole());
            userDto.setUserId(user.getUserId());
            return userDto;
        }


        if(user.getClass() == Student.class) {

            System.out.println("St"+user.getUserId());
            userDto = studentMapper.studentToStudentDTO((Student) user);
            return userDto;
        }
        if(user.getClass() == Teacher.class) {
            userDto = teacherMapper.teacherToTeacherDTO((Teacher) user);
            return userDto;
        }
       // if(user.getClass() == Admin.class)




        return null;
    }

    @Override
    public User userDTOToUser(UserDTO dto) {
        return null;
    }
}
