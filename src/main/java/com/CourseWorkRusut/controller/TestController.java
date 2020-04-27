package com.CourseWorkRusut.controller;


import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Specialty;
import com.CourseWorkRusut.model.User;
import com.CourseWorkRusut.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping(value = "/s")
    public ResponseEntity<Map<String,Object>> get() { //переделать

        Map<String, Object> map = new HashMap<>();

        List<String> list = new ArrayList<>();


        map.put("role",roleService.getAllRoles());
        map.put("positions",positionScienceDegreeService.getAllPositions());
        map.put("scienceDegrees",positionScienceDegreeService.getAllScienceDegree() );


        List<String> specialty = specialtyService.getAllSpecialty();

        List newList = new ArrayList();

        for(String spec: specialty){
            Map mapp = new HashMap();
            mapp.put("numberGroup",studyGroupService.getAllStudyGroupByNameSpecialty(spec));
            mapp.put("nameSpecialty",spec);
            newList.add(mapp);
        }



        map.put("specialty",newList );

        return new ResponseEntity<>(map, HttpStatus.OK); //переделать
    }


}
