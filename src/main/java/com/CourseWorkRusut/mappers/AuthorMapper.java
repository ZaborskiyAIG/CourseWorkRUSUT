package com.CourseWorkRusut.mappers;

import com.CourseWorkRusut.DTO.AuthorDTO;
import com.CourseWorkRusut.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO authorToAuthorDTO(Author author);

    Author authorDTOToAuthor(AuthorDTO dto);

}
