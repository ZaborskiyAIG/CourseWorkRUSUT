package com.CourseWorkRusut.service;

import com.CourseWorkRusut.DTO.ExamGroupDTO;

import java.util.List;

public interface ExamService {

    void save(ExamGroupDTO exam);

    List<ExamGroupDTO> getExamGroup(Long teacherId);

    ExamGroupDTO getExamStudents(Long teacherId, String numberGroup, String subject, String semester);

    void saveExamGroup(ExamGroupDTO examGroupDTO, Long teacherId);

}
