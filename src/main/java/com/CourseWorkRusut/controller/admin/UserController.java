package com.CourseWorkRusut.controller.admin;

import com.CourseWorkRusut.DTO.UserCounterDTO;
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
public class UserController {

    @Autowired
    private SubjectService subjectService;

    private UserService userService;

    private SpecialtyService specialtyService;

    private PositionScienceDegreeService positionScienceDegreeService;

    private StudyGroupService studyGroupService;

    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, SpecialtyService specialtyService, PositionScienceDegreeService positionScienceDegreeService, StudyGroupService studyGroupService, RoleService roleService) {
        this.userService = userService;
        this.specialtyService = specialtyService;
        this.positionScienceDegreeService = positionScienceDegreeService;
        this.studyGroupService = studyGroupService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<UserCounterDTO> getAllUser(@RequestParam(value = "offset", defaultValue = "0" )String offset,
                                                     @RequestParam(required = false) String search ) {
        UserCounterDTO userDTOS;
        if(search!=null){
            userDTOS = userService.searchUsers(search);
        } else {
            userDTOS = userService.getAllUser(offset);
        }

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @PutMapping(value = "/users")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO ){
        return new ResponseEntity<>(userService.update(userDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/classifiers")
    public ResponseEntity<Map<String,List>> getClassifiers() {
        Map<String, List> response = new HashMap<>();

        response.put("role",roleService.getAllRoles());
        response.put("positions",positionScienceDegreeService.getAllPositions());
        response.put("scienceDegrees",positionScienceDegreeService.getAllScienceDegree() );
        response.put("subject", subjectService.getAllSubject());

        List<String> specialty = specialtyService.getAllSpecialty();
        List<Map<String, Object>> specialtyList = new ArrayList<>();

        for(String spec: specialty){
            Map<String, Object> specialtyObject = new HashMap<>();

            specialtyObject.put("numberGroup",studyGroupService.getAllStudyGroupByNameSpecialty(spec));
            specialtyObject.put("nameSpecialty",spec);

            specialtyList.add(specialtyObject);
        }
        response.put("specialty",specialtyList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
