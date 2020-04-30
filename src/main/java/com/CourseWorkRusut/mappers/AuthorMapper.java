package com.CourseWorkRusut.mappers;

import com.CourseWorkRusut.DTO.AuthorDTO;
import com.CourseWorkRusut.model.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO authorToAuthorDTO(Author author);

    List<AuthorDTO> authorsToAuthorsDTO(List<Author> authors);

    Author authorDTOToAuthor(AuthorDTO dto);

}
