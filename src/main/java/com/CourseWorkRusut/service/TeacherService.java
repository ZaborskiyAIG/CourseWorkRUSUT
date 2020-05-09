package com.CourseWorkRusut.service;

import com.CourseWorkRusut.DTO.SubjectTeacherGroupDTO;
import com.CourseWorkRusut.DTO.UserCounterDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Teacher;
import com.CourseWorkRusut.model.User;

import java.util.List;

public interface TeacherService {

    User updateTeacher(Teacher teacher,List<SubjectTeacherGroupDTO> stg);

    UserCounterDTO getTeachersByParameters(String offset, String position, String degree);

    List<SubjectTeacherGroupDTO> getSubjectTeacherGroupDTO(Long TeacherId);


}
