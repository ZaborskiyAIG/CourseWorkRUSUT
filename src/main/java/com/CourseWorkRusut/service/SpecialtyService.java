package com.CourseWorkRusut.service;

import com.CourseWorkRusut.model.Specialty;

import java.util.List;

public interface SpecialtyService {

    Specialty getSpecialtyById(long id);

    List<Specialty> getAllSpecialty();
}
