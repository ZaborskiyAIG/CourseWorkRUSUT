package com.CourseWorkRusut.controller;

import com.CourseWorkRusut.DTO.InternshipDTO;
import com.CourseWorkRusut.DTO.LibraryDTO;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private UserService userService;

    @Autowired
    private InternshipService internshipService;

    @PostMapping(value = "/s/s",produces = "application/pdf")
    public ResponseEntity<InputStreamResource> updateUser(@RequestParam MultipartFile file) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "filename=" + file.getOriginalFilename());
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");


        System.out.println(file.getContentType());
        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
                new InputStreamResource(file.getInputStream()), headers, HttpStatus.OK);
     //   MultiFile
      //  return null;
        return response;
       // return new ResponseEntity( HttpStatus.OK);
    }




    @GetMapping(value = "/s/lib")
    public ResponseEntity<List<LibraryDTO>> library(@RequestParam(value = "offset", defaultValue = "0" )String offset)  {
         return new ResponseEntity<>(libraryService.getAllLibrary(offset), HttpStatus.OK);
    }



    @GetMapping(value = "/s")
    public ResponseEntity<List<User>> searc(@RequestParam(value = "search" )String search)  {
        return new ResponseEntity<>(userService.searchUsers(search), HttpStatus.OK);
    }

}
