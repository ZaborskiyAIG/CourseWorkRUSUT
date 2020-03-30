package com.CourseWorkRusut.controller;


import com.CourseWorkRusut.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //чем отличается от обычного @controller?
public class TestController {

    //@ResponseBody шо це таки?
    //не работало без jackson.core, чекнуть как это работает
    @PostMapping(value = "/user")
    public ResponseEntity<User> getUser(@RequestBody User user) { //requestBody? чек поле consumer


    System.out.println(user.toString());


   return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
