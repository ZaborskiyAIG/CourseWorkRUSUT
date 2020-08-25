package com.CourseWorkRusut.service;

import com.CourseWorkRusut.dto.LibraryCounterDTO;
import com.CourseWorkRusut.model.Library;

import java.util.List;

public interface LibraryService {

   Library getLibraryById(Long id);

   void updateLibrary(Library library);

   LibraryCounterDTO getAllLibrary(String offset);

   LibraryCounterDTO getAllLibrary(String offset, String search);

   void save(Library library);

   void delete(Long id);
}
