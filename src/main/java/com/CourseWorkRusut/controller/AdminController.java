package com.CourseWorkRusut.controller;

import com.CourseWorkRusut.model.Specialty;
import com.CourseWorkRusut.model.User;
import com.CourseWorkRusut.service.SpecialtyService;
import com.CourseWorkRusut.service.StudentService;
import com.CourseWorkRusut.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class AdminController {

    private UserService userService;

    private StudentService studentService;

    private SpecialtyService specialtyService;


    @Autowired
    public AdminController(UserService userService, StudentService studentService, SpecialtyService specialtyService) {
        this.userService = userService;
        this.studentService = studentService;
        this.specialtyService = specialtyService;
    }

    @PostMapping(value = "/admin/addUser")
    public ResponseEntity addUser(@RequestBody User user) { //requestBody? HttpServletRequest? чек поле consumer

        if(userService.getUserByLogin(user.getLogin())!=null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Such user already exists"); //как лучше отправлять сообщение об ошибке, через json или боди?
        }

        userService.register(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/generationNumberRecordBook")
    public ResponseEntity generationNumberRecordBook(Model model) {

    //    studentService.generationNumberRecordBook();

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/admin/allUsers")  //перенести в другое место
    public ResponseEntity<List<Map<String,String>>> getAllUser(@RequestParam(value = "offset", defaultValue = "0" )String offset) { //requestBody? HttpServletRequest? чек поле consumer
        return new ResponseEntity<>(userService.getAllUser(offset), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/allStudents")  //перенести в другое место
    public ResponseEntity<List<Map<String,String>>> getAllStudents(@RequestParam(value = "offset", defaultValue = "0" )String offset) { //requestBody? HttpServletRequest? чек поле consumer
        return new ResponseEntity<>(userService.getAllUser(offset), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/allTeacher")  //перенести в другое место
    public ResponseEntity<List<Map<String,String>>> getAllTeacher(@RequestParam(value = "offset", defaultValue = "0" )String offset) { //requestBody? HttpServletRequest? чек поле consumer
        return new ResponseEntity<>(userService.getAllUser(offset), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/counterUsers")  //перенести в другое место
    public ResponseEntity<Map<String,Long>> counterUser() { //requestBody? HttpServletRequest? чек поле consumer

        Map<String,Long> map =  new HashMap<>();
        map.put("counter",userService.contUsers());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/allSpecialties")  //перенести в другое место
    public ResponseEntity<List<Specialty>> allSpecialties() { //requestBody? HttpServletRequest? чек поле consumer
        return new ResponseEntity<>(specialtyService.getAllSpecialty(), HttpStatus.OK);
    }

    @PostMapping(value = "/admin/allUsers/updateUser")
    public ResponseEntity updateUser(@RequestBody User user){
        userService.update(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/admin/getUser")  //перенести в другое место
    public ResponseEntity<User> getUser(@RequestParam(value = "id") long id) { //requestBody? HttpServletRequest? чек поле consumer
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/admin/allUsers/updateUser")
    public ResponseEntity updateUsers(@RequestBody List<User> users){
        return new ResponseEntity(HttpStatus.OK);
    }


}
