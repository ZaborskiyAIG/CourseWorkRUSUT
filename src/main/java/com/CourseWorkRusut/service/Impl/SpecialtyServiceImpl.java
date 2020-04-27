package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.SpecialtyDAO;
import com.CourseWorkRusut.model.Specialty;
import com.CourseWorkRusut.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    private SpecialtyDAO specialtyDAO;

    @Autowired
    public SpecialtyServiceImpl(SpecialtyDAO specialtyDAO){
        this.specialtyDAO = specialtyDAO;
    }

    @Override
    @Transactional
    public Specialty getSpecialtyById(Long id) {
      return specialtyDAO.getSpecialtyById(id);
    }

    @Override
    @Transactional
    public Specialty getSpecialtyByName(String nameSpecialty) {
        return specialtyDAO.getSpecialtyByName(nameSpecialty);
    }

    @Override
    @Transactional
    public List<String> getAllSpecialty() {
        return specialtyDAO.getAllSpecialty();
    }
}
