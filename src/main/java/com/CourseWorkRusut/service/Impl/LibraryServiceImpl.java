package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.LibraryDAO;
import com.CourseWorkRusut.DTO.LibraryDTO;
import com.CourseWorkRusut.mappers.LibraryMapper;
import com.CourseWorkRusut.model.Library;
import com.CourseWorkRusut.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private LibraryDAO libraryDAO;

    @Autowired
    private LibraryMapper libraryMapper;

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
    public List<LibraryDTO> getAllLibrary(String offset) {

       // List<Library> libraries =libraryDAO.getAllLibrary(offset);
       // System.out.println("???"+libraries.get(0).getLibraryId());

      //  List<LibraryDTO> libraryDTOS = libraryMapper.libraryListToLibraryListDTO(libraries);
       // System.out.println("?"+libraryDTOS.get(0).getLibraryId());

        return libraryDAO.getAllLibrary(offset) ;
    }
}
