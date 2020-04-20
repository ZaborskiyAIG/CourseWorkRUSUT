package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.model.Specialty;

import java.util.List;

public interface SpecialtyDAO {

    Specialty getSpecialtyById(Long id);

    List<Specialty> getAllSpecialty();

}
