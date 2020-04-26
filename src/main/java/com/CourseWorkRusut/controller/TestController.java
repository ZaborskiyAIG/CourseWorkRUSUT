package com.CourseWorkRusut.controller;


import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.User;
import com.CourseWorkRusut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class TestController {

    @Autowired
    UserService userService;

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

    @PostMapping(value = "/s")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO ){
        userService.update(userDTO);
        return new ResponseEntity(HttpStatus.OK);
    }


}
