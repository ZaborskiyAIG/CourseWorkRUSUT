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
        return libraryDAO.getLibraryById(id);
    }

    @Override
    @Transactional
    public void updateLibrary(Library library) {

    }

    @Override
    @Transactional
    public LibraryCounterDTO getAllLibrary(String offset) {

        List<LibraryDTO> libraryDTOS =  libraryDAO.getAllLibrary(offset);

        Long count = libraryDAO.countLibrary();

        return new LibraryCounterDTO(libraryDTOS,count);
    }

    @Override
    @Transactional
    public void save(Library library) {
        libraryDAO.save(library);
    }

    @Override
    public void delete(Long id) {
        Library library = new Library();
        library.setLibraryId(id);
        libraryDAO.delete(library);
    }
}
