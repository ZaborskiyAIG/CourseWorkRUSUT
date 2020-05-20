package com.CourseWorkRusut.controller.teacher;

import com.CourseWorkRusut.DAO.InternshipDAO;
import com.CourseWorkRusut.DTO.*;
import com.CourseWorkRusut.model.Internship;
import com.CourseWorkRusut.model.LearningActivities;
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
    private ExamService examService;


    @Autowired
    private StudyGroupService studyGroupService;

    @Autowired
    private InternshipService internshipService;



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
            specialtyObject.put("count",specialtyService.getAmountSpecialty(spec));

            specialtyList.add(specialtyObject);
        }
        response.put("specialty",specialtyList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/stg/{id}")
    public ResponseEntity<SubjectTeacherGroupDTO> addSTG(@PathVariable Long id, @RequestBody SubjectTeacherGroupDTO subjectTeacherGroupDTO ){
        return new ResponseEntity<>(teacherService.updateSubjectTeacherGroup(subjectTeacherGroupDTO, id), HttpStatus.OK);
    }

    @PutMapping(value = "/stg/{id}")
    public ResponseEntity deleteSTG(@PathVariable Long id, @RequestBody SubjectTeacherGroupDTO subjectTeacherGroupDTO ){
        teacherService.deleteSubjectTeacherGroup(subjectTeacherGroupDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping(value = "/students")
    public ResponseEntity<List<StudentExamDTO>> getStudents(@RequestParam(value = "group") String numberGroup){
        return new ResponseEntity<>(studentService.getStudentsByNumberGroup(numberGroup), HttpStatus.OK);
    }

    @GetMapping(value = "/exam/{id}")
    public ResponseEntity<ExamGroupDTO> getExams(@PathVariable Long id,
                                                       @RequestParam String group,
                                                       @RequestParam String subject,
                                                       @RequestParam String semester ) {
        return new ResponseEntity<>(examService.getExamStudents(id,group, subject, semester), HttpStatus.OK);
    }

    @GetMapping(value = "/exams/{id}")
    public ResponseEntity<List<ExamGroupDTO>> getExam(@PathVariable Long id) {
        return new ResponseEntity<>(examService.getExamGroup(id), HttpStatus.OK);
    }

    @PutMapping(value = "/exams/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody ExamGroupDTO exam) {
        examService.saveExamGroup(exam, id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/exams/{id}")
    public ResponseEntity getUser(@PathVariable Long id, @RequestBody ExamGroupDTO exam) {
        examService.saveExamGroup(exam, id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/internship/{id}")
    public ResponseEntity updateInternsip(@PathVariable Long id, @RequestParam String mark) {
        Internship internship = internshipService.getInternshipsById(id);
        internship.getEmbeddableLearningInternship().setMark(mark);
        internshipService.update(internship);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/learning-activities/{id}")
    public ResponseEntity updateLearn(@PathVariable Long id, @RequestParam String mark) {
        LearningActivities learningActivities = internshipService.getLearningById(id);
        learningActivities.getEmbeddableLearningInternship().setMark(mark);
        internshipService.update(learningActivities);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/internship/{id}")
    public ResponseEntity<List<InternshipDTO>> adddInternship(@PathVariable Long id )  {
        return new ResponseEntity<>(internshipService.getInternshipsByTeacher(id), HttpStatus.OK);
    }

    @GetMapping(value = "/learning-activities/{id}")
    public ResponseEntity<List<LearningActivitiesDTO>> getActivities(@PathVariable Long id )  {
        return new ResponseEntity<>(internshipService.getLearningsByTeacher(id), HttpStatus.OK);
    }
}
