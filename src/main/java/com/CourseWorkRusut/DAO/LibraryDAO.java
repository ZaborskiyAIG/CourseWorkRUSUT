package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.model.Library;

import java.util.List;

public interface LibraryDAO {

    Library getLibraryById(Long id);

    void updateLibrary(Library library);

    List getAllLibrary(String offset);

    Long contLibrary();

    void save(Library library);

}
