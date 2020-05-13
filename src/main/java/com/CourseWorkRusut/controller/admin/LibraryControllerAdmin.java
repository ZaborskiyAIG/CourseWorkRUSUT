package com.CourseWorkRusut.controller.admin;

import com.CourseWorkRusut.DTO.InternshipDTO;
import com.CourseWorkRusut.DTO.LibraryCounterDTO;
import com.CourseWorkRusut.DTO.LibraryDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Author;
import com.CourseWorkRusut.model.Library;
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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
public class LibraryControllerAdmin {

    private LibraryService libraryService;

    public LibraryControllerAdmin(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


    @PostMapping(value = "/library",produces = "application/pdf")
    public ResponseEntity addLibrary(@RequestParam MultipartFile file, String name, String[] authors) throws IOException {


        System.out.println(name);

        System.out.println("sss"+authors[0]);

        Set<Author> list = new HashSet<>();

        for(int i = 0; i<authors.length; i++){
            Author author = new Author();

            String[] s = authors[i].split(" ");

            author.setName(s[0]);
            author.setSurname(s[1]);

            if(s.length>2)
                author.setMiddlename(s[2]);

            list.add(author);
        }




        Library library = new Library();
        library.setAuthors(list);

        library.setName(name);
        byte[] bytes = IOUtils.toByteArray(file.getInputStream());
        library.setBook(bytes);
        libraryService.save(library);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/library")
    public ResponseEntity<LibraryCounterDTO> library(@RequestParam(value = "offset", defaultValue = "0" )String offset)  {
         return new ResponseEntity<>(libraryService.getAllLibrary(offset), HttpStatus.OK);
    }


    @GetMapping(value = "/library/{id}", produces = "application/pdf")
    public ResponseEntity<InputStreamResource> library(@PathVariable Long id)  {

        Library library = libraryService.getLibraryById(id);

        InputStream inputStream = new ByteArrayInputStream(library.getBook());

        String fileName = library.getName()+".pdf";

        HttpHeaders headers = new HttpHeaders();


        headers.setContentType(MediaType.parseMediaType("application/pdf"));                    //сделать фильтр
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "attachment; filename=" + fileName);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Access-Control-Expose-Headers", "Content-Disposition");

        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
                new InputStreamResource(inputStream), headers, HttpStatus.OK);

        return  response ;
    }

    @DeleteMapping(value = "/library/delete/{id}")
    public ResponseEntity deleteLibrary(@PathVariable Long id){

        libraryService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
