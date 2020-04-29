package com.CourseWorkRusut.controller;


import com.CourseWorkRusut.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    PositionScienceDegreeService positionScienceDegreeService;

    @Autowired
    SpecialtyService specialtyService;

    @Autowired
    StudyGroupService studyGroupService;

    @Autowired
    RoleService roleService;

//    @GetMapping(value = "/s/{id}")
//    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
//
//
//
//        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/s")
//    public ResponseEntity<List<UserDTO>> getAllStudents(@RequestParam(value = "offset", defaultValue = "0" )String offset,
//                                                        @RequestParam(required = false) Long specialtyId,
//                                                        @RequestParam(required = false) Long groupId) {
//        return new ResponseEntity<>(userService.getStudentsByParameters(offset, groupId, specialtyId), HttpStatus.OK);
//    }

//    @PostMapping(value = "/s")
//    public ResponseEntity updateUser(@RequestBody UserDTO userDTO ){
//        userService.update(userDTO);
//        return new ResponseEntity(HttpStatus.OK);
//    }

  //  @PostMapping(value = "/s/{fileName:.+}",produces = "application/pdf")
    @PostMapping(value = "/s",produces = "application/pdf")
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


}
