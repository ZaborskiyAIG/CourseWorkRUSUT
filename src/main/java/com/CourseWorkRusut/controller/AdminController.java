package com.CourseWorkRusut.controller;

import com.CourseWorkRusut.DTO.InternshipDTO;
import com.CourseWorkRusut.DTO.LearningActivitiesDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Library;
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

    private PositionScienceDegreeService positionScienceDegreeService;

    private StudyGroupService studyGroupService;

    private RoleService roleService;

    private TeacherService teacherService ;

    private StudentService studentService ;

    @Autowired
    InternshipService internshipService;

    @Autowired
    LearningActivitiesService learningActivitiesService;

    @Autowired
    public AdminController(UserService userService, SpecialtyService specialtyService, PositionScienceDegreeService positionScienceDegreeService, StudyGroupService studyGroupService, RoleService roleService, TeacherService teacherService, StudentService studentService) {
        this.userService = userService;
        this.specialtyService = specialtyService;
        this.positionScienceDegreeService = positionScienceDegreeService;
        this.studyGroupService = studyGroupService;
        this.roleService = roleService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> getAllUser(@RequestParam(value = "offset", defaultValue = "0" )String offset,
                                                    @RequestParam(required = false) String search ) {
        List<UserDTO> userDTOS;
        if(search!=null){
            userDTOS = userService.searchUsers(search);  //изменить наименование метода
        } else {
            userDTOS = userService.getAllUser(offset);
        }

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }



    @GetMapping(value = "/students")
    public ResponseEntity<List<UserDTO>> getAllStudents(@RequestParam(value = "offset", defaultValue = "0" )String offset,
                                                        @RequestParam(required = false) String specialty,
                                                        @RequestParam(required = false) String group,
                                                        @RequestParam(required = false) String search) {

        List<UserDTO> userDTOS;
        if(search!=null){
            userDTOS = studentService.searchStudentByFullName(search);
        } else {
            userDTOS = studentService.getStudentsByParameters(offset, group, specialty);
        }

        return new ResponseEntity<>( userDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/teachers")
    public ResponseEntity<List<UserDTO>> getAllTeacher(@RequestParam(value = "offset", defaultValue = "0" )String offset) { //requestBody? HttpServletRequest? чек поле consumer
        return new ResponseEntity<>(teacherService.getTeachersByParameters(offset), HttpStatus.OK);
    }

    @GetMapping(value = "/counterUsers")
    public ResponseEntity<Map<String,Long>> counterUser(@RequestParam(value = "nameRole", defaultValue = "ROLE_USER") String nameRole){

        Map<String,Long> map =  new HashMap<>();
        map.put("counter",userService.contUsers(nameRole));

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

    @PutMapping(value = "/user/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {

        User user = new User();  //пофиксить, опустить формирование модели представления на слой ниже
        user.setUserId(id);

        userService.delete(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/classifiers")
    public ResponseEntity<Map<String,List>> get() { //переделать, опустить ниже, спросить у Игоря?

        Map<String, List> response = new HashMap<>();

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
        response.put("specialty",specialtyList);


        return new ResponseEntity<>(response, HttpStatus.OK); //переделать
    }








    @GetMapping(value = "/internships")
    public ResponseEntity<List<InternshipDTO>> getInternships()  {
        return new ResponseEntity<>(internshipService.getAllInternships(), HttpStatus.OK);
    }



    @GetMapping(value = "/learning-activities")
    public ResponseEntity<List<LearningActivitiesDTO>> getActivities()  {
        return new ResponseEntity<>(learningActivitiesService.getAllLearningActivities(), HttpStatus.OK);
    }

}
