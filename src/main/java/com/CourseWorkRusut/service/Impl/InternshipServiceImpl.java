package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.InternshipDAO;
import com.CourseWorkRusut.DTO.InternshipDTO;
import com.CourseWorkRusut.model.Internship;
import com.CourseWorkRusut.service.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class InternshipServiceImpl implements InternshipService {

    private InternshipDAO internshipDAO;

    @Autowired
    public InternshipServiceImpl(InternshipDAO internshipDAO) {
        this.internshipDAO = internshipDAO;
    }

    @Override
    @Transactional
    public List<InternshipDTO> getAllInternships() {
        return internshipDAO.getAllInternships();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Internship internship = new Internship();
        internship.setInternshipId(id);
        internshipDAO.delete(internship);
    }

}
