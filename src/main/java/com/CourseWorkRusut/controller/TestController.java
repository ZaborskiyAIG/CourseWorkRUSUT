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

    @GetMapping(value = "/s/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {



        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

}
