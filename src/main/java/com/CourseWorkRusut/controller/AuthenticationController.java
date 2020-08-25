package com.CourseWorkRusut.controller;

import com.CourseWorkRusut.dto.PasswordDTO;
import com.CourseWorkRusut.dto.UserDTO;
import com.CourseWorkRusut.model.User;
import com.CourseWorkRusut.security.jwt.JwtTokenProvider;
import com.CourseWorkRusut.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class AuthenticationController {

    private UserService userService;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public AuthenticationController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping(value = "/registration")
    public ResponseEntity registrationUser(@RequestBody User user) {

        if(userService.getUserByLogin(user.getLogin())!=null || userService.getUserByEmail(user.getEmail())!=null ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Such user already exists");
        }

        userService.register(user);

        String token = jwtTokenProvider.createToken(user.getLogin(), user.getRole().getNameRole());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping(value = "/login")
    public ResponseEntity loginUser(@RequestBody User user) {
        try {
            User validUser = userService.getUserByLogin(user.getLogin());

            if( validUser == null ){
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect login");
            }

            if(!bCryptPasswordEncoder.matches(user.getPassword(), validUser.getPassword()) ) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect password");
            }

            String token = jwtTokenProvider.createToken(user.getLogin(), validUser.getRole().getNameRole());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));

            Map<String, String> response = new HashMap<>();

            response.put("nameRole", validUser.getRole().getNameRole());
            response.put("token", token);
            response.put("login", user.getLogin());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (AuthenticationException e){
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
}

    @GetMapping(value = "/self/{login}")
    public ResponseEntity getMyself(@PathVariable String login){
        return new ResponseEntity<>(userService.getUserByLog(login), HttpStatus.OK);
    }

    @PutMapping(value = "/self")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO ){
        return new ResponseEntity<>(userService.update(userDTO), HttpStatus.OK);
    }

    @PutMapping(value = "/self/password/{id}")
    public ResponseEntity updatePassword(@PathVariable Long id, @RequestBody PasswordDTO dto ){

        User user = userService.getUserrById(id);

        if(bCryptPasswordEncoder.matches(dto.getOldPassword(), user.getPassword())){
            user.setPassword(bCryptPasswordEncoder.encode(dto.getNewPassword()));
            userService.update(user);
            return new ResponseEntity( HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad password");

        }


    }

    @GetMapping(value = "/ping")
    public ResponseEntity ping(){
        return ResponseEntity.status(HttpStatus.OK).body("Ok");

    }
}
