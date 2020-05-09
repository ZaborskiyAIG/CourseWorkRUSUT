package com.CourseWorkRusut.mappers.Impl;

import com.CourseWorkRusut.DTO.AdminDTO;
import com.CourseWorkRusut.DTO.StudentDTO;
import com.CourseWorkRusut.DTO.TeacherDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.mappers.AdminMapper;
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

    private StudentMapper studentMapper;

    private TeacherMapper teacherMapper;

    private AdminMapper adminMapper;


    @Autowired
    public UserMapperImpl(StudentMapper studentMapper, TeacherMapper teacherMapper, AdminMapper adminMapper) {
        this.studentMapper = studentMapper;
        this.teacherMapper = teacherMapper;
        this.adminMapper = adminMapper;
    }

    @Override
    public UserDTO userToUserDTO(User user) {

        UserDTO userDto;

        if(user.getClass() == User.class){
            System.out.println("Us"+user.getUserId());
            userDto = new UserDTO();
            userDto.setName(user.getName());
            userDto.setMiddlename(user.getMiddlename());
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
        if(user.getClass() == Admin.class){
            userDto = adminMapper.adminToAdminDTO((Admin) user);
            return userDto;
        }

        return null;
    }

    @Override
    public User userDTOToUser(UserDTO dto) {
        User user;

        if(dto.getClass() == UserDTO.class){
            user = new User();
            user.setName(dto.getName());
            user.setMiddlename(dto.getMiddlename());
            user.setSurname(dto.getSurname());
            user.setEmail(dto.getEmail());
            user.setRole(dto.getRole());
            user.setUserId(dto.getUserId());
            return user;
        }

        if(dto.getClass() == StudentDTO.class) {

            user = studentMapper.studentDTOToStudent((StudentDTO) dto);
            return user;
        }
        if(dto.getClass() == TeacherDTO.class) {
            user = teacherMapper.teacherDTOToTeacher((TeacherDTO) dto);
            return user;
        }
         if(dto.getClass() == AdminDTO.class){
             user = adminMapper.adminDTOToAdmin((AdminDTO) dto);
             return user;
         }

        return null;
    }
}
