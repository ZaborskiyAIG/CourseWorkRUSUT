package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.LibraryDAO;
import com.CourseWorkRusut.DTO.LibraryCounterDTO;
import com.CourseWorkRusut.DTO.LibraryDTO;
import com.CourseWorkRusut.model.Library;
import com.CourseWorkRusut.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private LibraryDAO libraryDAO;

    public LibraryServiceImpl(LibraryDAO libraryDAO){
        this.libraryDAO = libraryDAO;
    }

    @Override
    @Transactional
    public Library getLibraryById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void updateLibrary(Library library) {

    }

    @Override
    @Transactional
    public LibraryCounterDTO getAllLibrary(String offset) {

        List<LibraryDTO> libraryDTOS =  libraryDAO.getAllLibrary(offset);

        Long count = libraryDAO.contLibrary();

        return new LibraryCounterDTO(libraryDTOS,count);
    }

    @Override
    public void save(Library library) {
        libraryDAO.save(library);
    }
}
