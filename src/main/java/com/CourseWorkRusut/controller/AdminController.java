package com.CourseWorkRusut.controller;

import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.User;
import com.CourseWorkRusut.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private UserService userService;

    private SpecialtyService specialtyService;

    @Autowired
    PositionScienceDegreeService positionScienceDegreeService;

    @Autowired
    StudyGroupService studyGroupService;

    @Autowired
    RoleService roleService;

    @Autowired
    TeacherService teacherService ;

    @Autowired
    StudentService studentService ;

    @Autowired
    public AdminController(UserService userService, SpecialtyService specialtyService) {
        this.userService = userService;
        this.specialtyService = specialtyService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> getAllUser(@RequestParam(value = "offset", defaultValue = "0" )String offset) {
        return new ResponseEntity<>(userService.getAllUser(offset), HttpStatus.OK);
    }

    @GetMapping(value = "/students")
    public ResponseEntity<List<UserDTO>> getAllStudents(@RequestParam(value = "offset", defaultValue = "0" )String offset,
                                                                   @RequestParam(required = false) Long specialtyId,
                                                                   @RequestParam(required = false) Long groupId) {
        return new ResponseEntity<>(studentService.getStudentsByParameters(offset, groupId, specialtyId), HttpStatus.OK);
    }

    @GetMapping(value = "/teachers")
    public ResponseEntity<List<UserDTO>> getAllTeacher(@RequestParam(value = "offset", defaultValue = "0" )String offset) { //requestBody? HttpServletRequest? чек поле consumer
        return new ResponseEntity<>(teacherService.getTeachersByParameters(offset), HttpStatus.OK);
    }



    @GetMapping(value = "/counterUsers")
    public ResponseEntity<Map<String,Long>> counterUser() {

        Map<String,Long> map =  new HashMap<>();
        map.put("counter",userService.contUsers());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping(value = "/users/updateUser")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO ){
        return new ResponseEntity<>(userService.update(userDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/users/updateUsers")
    public ResponseEntity updateUsers(@RequestBody List<User> users){
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }


  /*  @PostMapping(value = "/addUser")
    public ResponseEntity addUser(@RequestBody User user) {

        if(userService.getUserByLogin(user.getLogin())!=null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Such user already exists");
        }

        userService.register(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    @GetMapping(value = "/classifiers")
    public ResponseEntity<Map<String,Object>> get() { //переделать

        Map<String, Object> response = new HashMap<>();

        response.put("role",roleService.getAllRoles());
        response.put("positions",positionScienceDegreeService.getAllPositions());
        response.put("scienceDegrees",positionScienceDegreeService.getAllScienceDegree() );


        List<String> specialty = specialtyService.getAllSpecialty();
        List<Map<String, Object>> specialtyList = new ArrayList<>();

        for(String spec: specialty){
            Map<String, Object> specialtyObject = new HashMap<>();

            specialtyObject.put("numberGroup",studyGroupService.getAllStudyGroupByNameSpecialty(spec));
            specialtyObject.put("nameSpecialty",spec);

            specialtyList.add(specialtyObject);
        }

        response.put("specialty",specialtyList );

        return new ResponseEntity<>(response, HttpStatus.OK); //переделать
    }


}
