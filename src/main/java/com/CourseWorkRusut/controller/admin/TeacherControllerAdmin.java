package com.CourseWorkRusut.controller.admin;

import com.CourseWorkRusut.DTO.UserCounterDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
public class TeacherControllerAdmin {

    private TeacherService teacherService ;

    @Autowired
    public TeacherControllerAdmin(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(value = "/teachers")
    public ResponseEntity<UserCounterDTO> getAllTeacher(@RequestParam(value = "offset", defaultValue = "0" )String offset, //requestBody? HttpServletRequest? чек поле consumer
                                                        @RequestParam(required = false) String position,
                                                        @RequestParam(required = false) String degree) {
        return new ResponseEntity<>(teacherService.getTeachersByParameters(offset, position, degree), HttpStatus.OK);
    }

}
