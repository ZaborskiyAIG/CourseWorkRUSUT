package com.CourseWorkRusut.controller.student;

import com.CourseWorkRusut.DTO.*;
import com.CourseWorkRusut.model.*;
import com.CourseWorkRusut.service.ExamService;
import com.CourseWorkRusut.service.InternshipService;
import com.CourseWorkRusut.service.LearningActivitiesService;
import com.CourseWorkRusut.service.TeacherService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/student")
public class ExamControllerStudent {

    @Autowired
    ExamService examService;

    @Autowired
    InternshipService internshipService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    LearningActivitiesService learningActivitiesService;

    @GetMapping(value = "/exams/{id}")
    public ResponseEntity<List<StudentExamsDTO>> getExam(@PathVariable Long id, @RequestParam String semester ) {
        return new ResponseEntity<>(examService.getStudentExams(id, semester), HttpStatus.OK);
    }


    @PostMapping(value = "/internship/{id}",produces = "application/pdf")
    public ResponseEntity adddddInternship(@PathVariable Long id, @RequestParam MultipartFile file, String topic, String semester, Long placeId, Long teacherId) throws IOException {

        Internship internship = new Internship();

        EmbeddableLearningInternship emb = new EmbeddableLearningInternship();

        byte[] bytes = IOUtils.toByteArray(file.getInputStream());
        emb.setReport(bytes);

        emb.setTopic(topic);

        internship.setEmbeddableLearningInternship(emb);

        internship.setSemester(examService.getSemesterByIdStudentAndNumber(id, semester));

        PlacePractice placePractice = new PlacePractice(); //вообще этим админ должен заниматься, имхо
        placePractice.setPlacePracticeId(placeId);

        internship.setPlacePractice(placePractice);

        Teacher teacher = new Teacher();
        teacher.setUserId(teacherId);
        internship.setTeacher(teacher);

        internshipService.save(internship);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/learning-activities/{id}",produces = "application/pdf")
    public ResponseEntity addddLearning(@PathVariable Long id, @RequestParam MultipartFile file, String topic, String semester, TeacherNameDTO dto, String type) throws IOException {

        LearningActivities len = new LearningActivities();

        EmbeddableLearningInternship emb = new EmbeddableLearningInternship();
       // emb.setMark(mark);

        byte[] bytes = IOUtils.toByteArray(file.getInputStream());
        emb.setReport(bytes);

        emb.setTopic(topic);

        len.setEmbeddableLearningInternship(emb);
        len.setLearningActivitiesType(learningActivitiesService.getLearningByType(type));
        len.setSemester(examService.getSemesterByIdStudentAndNumber(id, semester));

        Teacher teacher = new Teacher();
        teacher.setUserId(dto.getId());
        len.setTeacher(teacher);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/internship/{id}")
    public ResponseEntity<List<InternshipDTO>> adddInternship(@PathVariable Long id )  {
        return new ResponseEntity<>(internshipService.getInternshipsByStudent(id), HttpStatus.OK);
    }

    @GetMapping(value = "/learning-activities/{id}")
    public ResponseEntity<List<LearningActivitiesDTO>> getActivities(@PathVariable Long id )  {
        return new ResponseEntity<>(internshipService.getLearningsByStudent(id), HttpStatus.OK);
    }

    @GetMapping(value = "/classifiers")
    public ResponseEntity<Map<String,List>> getClassifiers() {

        Map<String, List> response = new HashMap<>();
        response.put("place", internshipService.getAllPlace());
        response.put("teachers", teacherService.getFullNameTeachers());
        response.put("learning",learningActivitiesService.getTypeLearning());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
