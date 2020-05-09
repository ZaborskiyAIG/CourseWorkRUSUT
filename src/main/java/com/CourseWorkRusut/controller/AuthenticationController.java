package com.CourseWorkRusut.controller;

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
    public ResponseEntity registrationUser(@RequestBody User user) { //requestBody? HttpServletRequest? чек поле consumer

        if(userService.getUserByLogin(user.getLogin())!=null || userService.getUserByEmail(user.getEmail())!=null ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Such user already exists");
        }

        userService.register(user);

        //надо сделать авторизацию сразу после регистрации

        /*String token = jwtTokenProvider.createToken(user.getLogin(), Student.getNameRole());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
        request.getSession();
        authToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Map<Object, Object> response = new HashMap<>();
        response.put("role", Student.getNameRole());
        response.put("token", token);*/
        //String token = jwtTokenProvider.createToken(user.getLogin(), String.valueOf(user.getAuthorities().iterator().next()));
        String token = jwtTokenProvider.createToken(user.getLogin(), user.getRole().getNameRole());
        // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
        Map<String, String> response = new HashMap<>();
        //  response.put("role", String.valueOf(validUser.getAuthorities().iterator().next()));
        response.put("token", token);
        return new ResponseEntity<>(response, HttpStatus.OK);
     //   return new ResponseEntity<>(HttpStatus.OK);
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

        } catch (AuthenticationException e){  //разобрать обработку ошибок в контроллере
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @GetMapping(value = "/self/{login}")
    public ResponseEntity getMyself(@PathVariable String login){
        return new ResponseEntity<>(userService.getUserByLog(login), HttpStatus.OK);
    }
}
