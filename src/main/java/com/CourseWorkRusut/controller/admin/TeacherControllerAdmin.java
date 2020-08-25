package com.CourseWorkRusut.controller.admin;

import com.CourseWorkRusut.dto.UserCounterDTO;
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
    public ResponseEntity<UserCounterDTO> getAllTeacher(@RequestParam(value = "offset", defaultValue = "0" )String offset,
                                                        @RequestParam(required = false) String position,
                                                        @RequestParam(required = false) String degree,
                                                        @RequestParam(required = false) String search) {

        UserCounterDTO userDTOS;
        if(search!=null){
            userDTOS = teacherService.searchTeacherByFullName(search);
        } else {
            userDTOS = teacherService.getTeachersByParameters(offset, position, degree);
        }

        return new ResponseEntity<>( userDTOS, HttpStatus.OK);
    }


}
