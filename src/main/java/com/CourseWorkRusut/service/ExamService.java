package com.CourseWorkRusut.service;

import com.CourseWorkRusut.dto.ExamGroupDTO;
import com.CourseWorkRusut.dto.StudentExamsDTO;
import com.CourseWorkRusut.model.Semester;

import java.util.List;

public interface ExamService {

    void save(ExamGroupDTO exam);

    List<ExamGroupDTO> getExamGroup(Long teacherId);

    ExamGroupDTO getExamStudents(Long teacherId, String numberGroup, String subject, String semester);

    void saveExamGroup(ExamGroupDTO examGroupDTO, Long teacherId);

    List<StudentExamsDTO> getStudentExams(Long id, String semester);

    Semester getSemesterByIdStudentAndNumber(Long id, String semester);



}
