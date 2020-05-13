package com.CourseWorkRusut.controller.teacher;

import com.CourseWorkRusut.model.Author;
import com.CourseWorkRusut.model.Library;
import com.CourseWorkRusut.service.LibraryService;
import com.CourseWorkRusut.service.SpecialtyService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(value = "/teacher")
public class LibraryControllerTeacher {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    SpecialtyService specialtyService;

    @PostMapping(value = "/library/{specialty}",produces = "application/pdf")
    public ResponseEntity addLibrary(@PathVariable String specialty, @RequestParam MultipartFile file, String name, String[] authors) throws IOException {

        Set<Author> list = new HashSet<>();

        for(int i = 0; i<authors.length; i++){
            Author author = new Author();

            String[] s = authors[i].split(" ");
            author.setName(new String (s[0].getBytes ("iso-8859-1"), "UTF-8"));
            author.setSurname(new String (s[1].getBytes ("iso-8859-1"), "UTF-8"));

            if(s.length>2) {
                author.setMiddlename(new String (s[2].getBytes ("iso-8859-1"), "UTF-8"));
            }
            list.add(author);
        }

        Library library = new Library();
        library.setAuthors(list);

        library.setName(new String (name.getBytes ("iso-8859-1"), "UTF-8"));
        byte[] bytes = IOUtils.toByteArray(file.getInputStream());
        library.setBook(bytes);
        library.setSpecialty(specialtyService.getSpecialtyByName(specialty));
        libraryService.save(library);
        return new ResponseEntity(HttpStatus.OK);
    }



}
