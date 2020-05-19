package com.CourseWorkRusut.controller.admin;

import com.CourseWorkRusut.DTO.InternshipDTO;
import com.CourseWorkRusut.DTO.LibraryCounterDTO;
import com.CourseWorkRusut.DTO.LibraryDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Author;
import com.CourseWorkRusut.model.Library;
import com.CourseWorkRusut.model.Specialty;
import com.CourseWorkRusut.model.User;
import com.CourseWorkRusut.service.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;


@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
public class LibraryControllerAdmin {

    private LibraryService libraryService;

    public LibraryControllerAdmin(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Autowired
    SpecialtyService specialtyService;

    @PostMapping(value = "/library/{specialty}",produces = "application/pdf")
    public ResponseEntity addLibrary(@PathVariable String specialty, @RequestParam MultipartFile file, String name, String[] authors) throws IOException {

        Set<Author> list = new HashSet<>();

        for(int i = 0; i<authors.length; i++){
            Author author = new Author();

            String[] s = authors[i].split(" ");
          //  String s0 = new String(s[0].getBytes(), StandardCharsets.UTF_8);
         //   String s1 = new String(s[1].getBytes(), StandardCharsets.UTF_8);
            author.setName(new String (s[0].getBytes ("iso-8859-1"), "UTF-8"));
            author.setSurname(new String (s[1].getBytes ("iso-8859-1"), "UTF-8"));

            if(s.length>2) {
           //     String s2 = new String(s[2].getBytes(), "UTF-8");
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

    @GetMapping(value = "/library")
    public ResponseEntity<LibraryCounterDTO> library(@RequestParam(value = "offset", defaultValue = "0" )String offset, @RequestParam(required = false) String search)  {

        LibraryCounterDTO library;

        if(search!=null){
            library = libraryService.getAllLibrary(offset);
        } else {
            library = libraryService.getAllLibrary(offset, search);
        }

         return new ResponseEntity<>(library, HttpStatus.OK);
    }


    @GetMapping(value = "/library/{id}", produces = "application/pdf")
    public ResponseEntity<InputStreamResource> library(@PathVariable Long id) throws UnsupportedEncodingException {

        Library library = libraryService.getLibraryById(id);
        InputStream inputStream = new ByteArrayInputStream(library.getBook());
        String fileName = library.getName();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType("application/pdf; charset=UTF-8"));                    //сделать фильтр

        String filen = URLEncoder.encode(fileName.replace(" ", "_"), "UTF-8");

        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "attachment; filename=" + filen +".pdf");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Access-Control-Expose-Headers", "Content-Disposition");

        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
                new InputStreamResource(inputStream), headers, HttpStatus.OK);

        return  response ;
    }

    @DeleteMapping(value = "/library/{id}")
    public ResponseEntity deleteLibrary(@PathVariable Long id){

        libraryService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
