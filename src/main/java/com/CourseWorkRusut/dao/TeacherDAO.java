package com.CourseWorkRusut.dao;

import com.CourseWorkRusut.dto.TeacherNameDTO;
import com.CourseWorkRusut.model.SubjectTeacherGroup;
import com.CourseWorkRusut.model.User;

import java.util.List;

public interface TeacherDAO {

    List<User> getTeachersByParameters(String offset, String position, String degree);

    Long counterTeachersByParameters(String position, String degree);

    void saveSubjectTeacherGroup(SubjectTeacherGroup subjectTeacherGroup);

    void deleteSubjectTeacherGroup(SubjectTeacherGroup subjectTeacherGroup);

    List<SubjectTeacherGroup>  getSubjectTeacherGroupByNumberGroupBySubject(List<String> numberGroup, String subject);

    List<SubjectTeacherGroup> getSTGByTeacherId(Long id, Long subId, Long groupId);

    List<String> getNumberGroupByTeacherId(Long id);

    List<TeacherNameDTO> getFullNameTeachers();

    Long counterTeacherByFullName(String search);

    List<User> searchTeacherByFullName(String search);
}
