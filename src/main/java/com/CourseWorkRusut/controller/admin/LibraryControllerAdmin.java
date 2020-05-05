package com.CourseWorkRusut.controller.admin;

import com.CourseWorkRusut.DTO.InternshipDTO;
import com.CourseWorkRusut.DTO.LibraryCounterDTO;
import com.CourseWorkRusut.DTO.LibraryDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Library;
import com.CourseWorkRusut.model.User;
import com.CourseWorkRusut.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
public class LibraryControllerAdmin {

    @Autowired
    private LibraryService libraryService;

    @PostMapping(value = "/library",produces = "application/pdf")
    public ResponseEntity<InputStreamResource> addLibrary(@RequestParam MultipartFile file) throws IOException {

        Library library = new Library();

        library.setBook(new byte[file.getInputStream().available()]);

        libraryService.save(library);

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType("application/pdf"));
//        headers.add("Access-Control-Allow-Origin", "*");
//        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
//        headers.add("Access-Control-Allow-Headers", "Content-Type");
//        headers.add("Content-Disposition", "filename=" + file.getOriginalFilename());
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//
//
//        System.out.println(file.getContentType());
//        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
//                new InputStreamResource(file.getInputStream()), headers, HttpStatus.OK);

        return null;
     //   return response;
        // return new ResponseEntity( HttpStatus.OK);
    }

//    @GetMapping(value = "/library",produces = "application/pdf")
//    public ResponseEntity<InputStreamResource> updateUser(@RequestParam MultipartFile file) throws IOException {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType("application/pdf"));
//        headers.add("Access-Control-Allow-Origin", "*");
//        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
//        headers.add("Access-Control-Allow-Headers", "Content-Type");
//        headers.add("Content-Disposition", "filename=" + file.getOriginalFilename());
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//
//
//        System.out.println(file.getContentType());
//        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
//                new InputStreamResource(file.getInputStream()), headers, HttpStatus.OK);
//
//        return response;
//        // return new ResponseEntity( HttpStatus.OK);
//    }


    @GetMapping(value = "/library")
    public ResponseEntity<LibraryCounterDTO> library(@RequestParam(value = "offset", defaultValue = "0" )String offset)  {
         return new ResponseEntity<>(libraryService.getAllLibrary(offset), HttpStatus.OK);
    }


    @GetMapping(value = "/library/{id}")
    public ResponseEntity library(@PathVariable Long id)  {

        return null;


    }

}
