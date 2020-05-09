package com.CourseWorkRusut.controller.teacher;

import com.CourseWorkRusut.DTO.StudentDTO;
import com.CourseWorkRusut.DTO.UserCounterDTO;
import com.CourseWorkRusut.service.StudentService;
import com.CourseWorkRusut.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/teacher")
public class StudentControllerTeacher {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping(value = "/students")
    public ResponseEntity<List<StudentDTO>> getAllUser(@RequestParam String  group ) {

        return new ResponseEntity<>(studentService.getStudentsByNumberGroup(group), HttpStatus.OK);
    }

}
