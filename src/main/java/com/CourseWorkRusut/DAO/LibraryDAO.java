package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.DTO.LibraryDTO;
import com.CourseWorkRusut.model.Library;

import java.util.List;

public interface LibraryDAO {

    Library getLibraryById(Long id);

    void updateLibrary(Library library);

    List<LibraryDTO> getAllLibrary(String offset);

}
