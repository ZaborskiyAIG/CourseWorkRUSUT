package com.CourseWorkRusut.controller.teacher;

import com.CourseWorkRusut.DTO.*;
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
@RequestMapping(value = "/teacher")
public class StudentControllerTeacher {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudyGroupService studyGroupService;

    @GetMapping(value = "/students")
    public ResponseEntity<List<StudentDTO>> getStudents(@RequestParam String  group ) {

        return new ResponseEntity<>(studentService.getStudentsByNumberGroup(group), HttpStatus.OK);
    }

    @GetMapping(value = "/classifiers")
    public ResponseEntity<Map<String,List>> getClassifiers() {
        Map<String, List> response = new HashMap<>();

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

    @PostMapping(value = "/stg/{id}")
    public ResponseEntity<List<SubjectTeacherGroupDTO>> addSTG(@PathVariable Long id, @RequestBody List<SubjectTeacherGroupDTO> subjectTeacherGroupDTO ){
        return new ResponseEntity<>(teacherService.updateSubjectTeacherGroup(subjectTeacherGroupDTO, id), HttpStatus.OK);
    }

    @PutMapping(value = "/stg/{id}")
    public ResponseEntity deleteSTG(@PathVariable Long id, @RequestBody SubjectTeacherGroupDTO subjectTeacherGroupDTO ){
        teacherService.deleteSubjectTeacherGroup(subjectTeacherGroupDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

}
