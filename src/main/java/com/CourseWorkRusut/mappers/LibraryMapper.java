package com.CourseWorkRusut.mappers;

import com.CourseWorkRusut.DTO.LibraryDTO;
import com.CourseWorkRusut.model.Library;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class})
public interface LibraryMapper {


    LibraryDTO libraryToLibraryDTO(Library library);

    Library libraryDTOToLibrary(LibraryDTO dto);

}
