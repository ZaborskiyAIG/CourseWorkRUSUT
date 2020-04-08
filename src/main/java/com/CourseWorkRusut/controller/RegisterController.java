package com.CourseWorkRusut.controller;

import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.User;
import com.CourseWorkRusut.security.jwt.JwtTokenProvider;
import com.CourseWorkRusut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RegisterController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/registration")
   // public ResponseEntity<User> getUser(@RequestBody User user) { //requestBody? чек поле consumer
    public ResponseEntity registrationUser(@RequestBody User user) {
        userService.register(user);
        String token = jwtTokenProvider.createToken(user.getLogin(), "ROLE_STUDENT");
        Map<Object, Object> response = new HashMap<>();
        response.put("username", user.getLogin());
        response.put("token", token);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity loginUser(@RequestBody User user) {

        try {
            System.out.println("1");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));

            System.out.println("2");
            String token = jwtTokenProvider.createToken(user.getLogin(), "ROLE_STUDENT");
            System.out.println("3");
            Map<Object, Object> response = new HashMap<>();
            response.put("login", user.getLogin());
            response.put("token", token);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (AuthenticationException e){
            System.err.println(e);
           // throw new BadCredentialsException("Invalid username or password"); не работает, почему?

            return new ResponseEntity(HttpStatus.resolve(500));
        }

    }

}
