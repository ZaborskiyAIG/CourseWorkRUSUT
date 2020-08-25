package com.CourseWorkRusut.dao;

import com.CourseWorkRusut.model.Specialty;

import java.util.List;

public interface SpecialtyDAO {

    Specialty getSpecialtyById(Long id);

    Specialty getSpecialtyByName(String nameSpecialty);

    List<String> getAllSpecialty();

    Integer getAmountSpecialty(String specialty);

}
