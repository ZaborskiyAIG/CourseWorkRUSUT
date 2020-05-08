package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.DTO.InternshipDTO;
import com.CourseWorkRusut.model.Internship;

import java.util.List;

public interface InternshipDAO {

    List<InternshipDTO> getAllInternships();

    void delete(Internship internship);

}
