package com.CourseWorkRusut.service;

import com.CourseWorkRusut.DTO.InternshipDTO;
import com.CourseWorkRusut.model.Internship;

import java.util.List;

public interface InternshipService {

    List<InternshipDTO> getAllInternships();

    void delete(Long id);
}
