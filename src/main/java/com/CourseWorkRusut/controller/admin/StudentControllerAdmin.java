package com.CourseWorkRusut.controller.admin;


import com.CourseWorkRusut.DTO.InternshipDTO;
import com.CourseWorkRusut.DTO.LearningActivitiesDTO;
import com.CourseWorkRusut.DTO.UserCounterDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.PlacePractice;
import com.CourseWorkRusut.service.InternshipService;
import com.CourseWorkRusut.service.LearningActivitiesService;
import com.CourseWorkRusut.service.StudentService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
public class StudentControllerAdmin {

    private InternshipService internshipService;

    private LearningActivitiesService learningActivitiesService;

    private StudentService studentService;

    @Autowired
    public StudentControllerAdmin(InternshipService internshipService, LearningActivitiesService learningActivitiesService, StudentService studentService) {
        this.internshipService = internshipService;
        this.learningActivitiesService = learningActivitiesService;
        this.studentService = studentService;
    }

    @GetMapping(value = "/students")
    public ResponseEntity<UserCounterDTO> getAllStudents(@RequestParam(value = "offset", defaultValue = "0" )String offset,
                                                        @RequestParam(required = false) String specialty,
                                                        @RequestParam(required = false) String group,
                                                        @RequestParam(required = false) String search) {

        UserCounterDTO userDTOS;
        if(search!=null){
            System.out.println("sss");
            userDTOS = studentService.searchStudentByFullName(search);
        } else {
            System.out.println("спец"+specialty);
            System.out.println("group"+group);
            userDTOS = studentService.getStudentsByParameters(offset, group, specialty);
        }

        return new ResponseEntity<>( userDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/internships")
    public ResponseEntity<List<InternshipDTO>> getInternships()  {
        return new ResponseEntity<>(internshipService.getAllInternships(), HttpStatus.OK);
    }

    @GetMapping(value = "/learning-activities")
    public ResponseEntity<List<LearningActivitiesDTO>> getActivities()  {
        return new ResponseEntity<>(learningActivitiesService.getAllLearningActivities(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/internships/delete/{id}")
    public ResponseEntity deleteInternships(@PathVariable Long id)  {
        internshipService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/learning-activities/delete/{id}")
    public ResponseEntity deleteActivities(@PathVariable Long id)  {
        learningActivitiesService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/place_practice")
    public ResponseEntity addPlacePractice(@RequestBody PlacePractice placePractice){
            internshipService.save(placePractice);
            return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/place_practice")
    public ResponseEntity<List<PlacePractice>> getPlacePractice(@RequestParam(value = "offset", defaultValue = "0" )String offset){
        return new ResponseEntity<>(internshipService.getAllPlace(offset), HttpStatus.OK);
    }

    @GetMapping(value = "/place_practice/{id}")
    public ResponseEntity<PlacePractice> getPlacePractice(@PathVariable Long id){
        return new ResponseEntity<>(internshipService.getPlace(id), HttpStatus.OK);
    }

    @PutMapping(value = "/place_practice")
    public ResponseEntity updatePlacePractice(@RequestBody PlacePractice placePractice){
        internshipService.updatePlace(placePractice);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/place_practice/{id}")
    public ResponseEntity deletePlacePractice(@PathVariable Long id){
        internshipService.deletePlace(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
