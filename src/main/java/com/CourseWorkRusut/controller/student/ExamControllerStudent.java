package com.CourseWorkRusut.controller.student;

import com.CourseWorkRusut.DTO.ExamGroupDTO;
import com.CourseWorkRusut.DTO.StudentExamsDTO;
import com.CourseWorkRusut.model.*;
import com.CourseWorkRusut.service.ExamService;
import com.CourseWorkRusut.service.InternshipService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(value = "/student")
public class ExamControllerStudent {

    @Autowired
    ExamService examService;

    @Autowired
    InternshipService internshipService;

    @GetMapping(value = "/exams/{id}")
    public ResponseEntity<List<StudentExamsDTO>> getExam(@PathVariable Long id, @RequestParam String semester ) {
        return new ResponseEntity<>(examService.getStudentExams(id, semester), HttpStatus.OK);
    }


    @PostMapping(value = "/internship/{id}",produces = "application/pdf")
    public ResponseEntity addInternship(@PathVariable Long id, @RequestParam MultipartFile file, String mark, String topic, String semester, PlacePractice place ) throws IOException {

        Internship internship = new Internship();

        EmbeddableLearningInternship emb = new EmbeddableLearningInternship();
        emb.setMark(mark);

        byte[] bytes = IOUtils.toByteArray(file.getInputStream());
        emb.setReport(bytes);

        emb.setTopic(topic);

        internship.setEmbeddableLearningInternship(emb);

        internship.setSemester(examService.getSemesterByIdStudentAndNumber(id, semester));

    //    PlacePractice placePractice = new PlacePractice(); //вообще этим админ должен заниматься, имхо
//        placePractice.setAddress();
//        placePractice.setCompanyName();
//        placePractice.setInternshipDirector();
//        placePractice.setPhone();

        internship.setPlacePractice(place);

        internshipService.save(internship);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/internship/{id}")
    public ResponseEntity<List<Internship>> addInternship(@PathVariable Long id )  {
        return new ResponseEntity<>(internshipService.getInternshipsByStudent(id), HttpStatus.OK);
    }

    @GetMapping(value = "/learning-activities/{id}")
    public ResponseEntity<List<LearningActivities>> getActivities(@PathVariable Long id )  {
        return new ResponseEntity<>(internshipService.getLearningsByStudent(id), HttpStatus.OK);
    }

}
