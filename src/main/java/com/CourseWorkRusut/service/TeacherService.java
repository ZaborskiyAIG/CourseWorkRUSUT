package com.CourseWorkRusut.service;

import com.CourseWorkRusut.DTO.SubjectTeacherGroupDTO;
import com.CourseWorkRusut.DTO.TeacherDTO;
import com.CourseWorkRusut.DTO.UserCounterDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.SubjectTeacherGroup;
import com.CourseWorkRusut.model.Teacher;
import com.CourseWorkRusut.model.User;

import java.util.List;

public interface TeacherService {

    User updateTeacher(Teacher teacher);

    UserCounterDTO getTeachersByParameters(String offset, String position, String degree);

    List<SubjectTeacherGroupDTO> getSubjectTeacherGroupDTO(Long TeacherId);

    SubjectTeacherGroupDTO updateSubjectTeacherGroup(SubjectTeacherGroupDTO subjectTeacherGroupDTO, Long id);

    void deleteSubjectTeacherGroup(SubjectTeacherGroupDTO dto, Long teacherId);

}
