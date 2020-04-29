package com.CourseWorkRusut.service;

import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.StudyGroup;
import com.CourseWorkRusut.model.User;

import java.util.List;

public interface StudentService {

   // String generationNumberStudyBook(String entryDate, StudyGroup studyGroup);

    User updateStudent(Student student, User user);

    List<UserDTO> getStudentsByParameters(String offset, Long groupId, Long specialtyId);

}
