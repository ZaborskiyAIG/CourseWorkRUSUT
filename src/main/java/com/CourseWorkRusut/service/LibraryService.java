package com.CourseWorkRusut.service;

import com.CourseWorkRusut.model.Library;

import java.util.List;

public interface LibraryService {

   Library getLibraryById(Long id);

   void updateLibrary(Library library);

   List<Library> getAllLibrary();
}