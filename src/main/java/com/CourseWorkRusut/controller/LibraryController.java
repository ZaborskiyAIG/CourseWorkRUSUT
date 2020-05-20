package com.CourseWorkRusut.controller;

import com.CourseWorkRusut.DTO.LibraryCounterDTO;
import com.CourseWorkRusut.model.Internship;
import com.CourseWorkRusut.model.LearningActivities;
import com.CourseWorkRusut.model.Library;
import com.CourseWorkRusut.service.InternshipService;
import com.CourseWorkRusut.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@CrossOrigin
@RestController
public class LibraryController {

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private LibraryService libraryService;

    @GetMapping(value = "/library/{id}", produces = "application/pdf")
    public ResponseEntity<InputStreamResource> library(@PathVariable Long id) throws UnsupportedEncodingException {

        Library library = libraryService.getLibraryById(id);
        InputStream inputStream = new ByteArrayInputStream(library.getBook());
        String fileName = library.getName();
        HttpHeaders headers = new HttpHeaders();


        headers.setContentType(MediaType.parseMediaType("application/pdf; charset=UTF-8"));
        String filen = URLEncoder.encode(fileName.replace(" ", "_"), "UTF-8");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "attachment; filename=" + filen +".pdf");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Access-Control-Expose-Headers", "Content-Disposition");

        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
                new InputStreamResource(inputStream), headers, HttpStatus.OK);

        return  response ;
    }

//    @GetMapping(value = "/library")
//    public ResponseEntity<LibraryCounterDTO> library(@RequestParam(value = "offset", defaultValue = "0" )String offset)  {
//        return new ResponseEntity<>(libraryService.getAllLibrary(offset), HttpStatus.OK);
//    }

    @GetMapping(value = "/library")
    public ResponseEntity<LibraryCounterDTO> library(@RequestParam(value = "offset", defaultValue = "0" )String offset, @RequestParam(required = false) String search)  {

        LibraryCounterDTO library;

        if(search!=null){
            library = libraryService.getAllLibrary(offset);
        } else {
            library = libraryService.getAllLibrary(offset, search);
        }

        return new ResponseEntity<>(library, HttpStatus.OK);
    }


    @GetMapping(value = "/internship/file/{id}", produces = "application/pdf")
    public ResponseEntity<InputStreamResource> internship(@PathVariable Long id) throws UnsupportedEncodingException {

        Internship internship = internshipService.getInternshipsById(id) ;
        InputStream inputStream = new ByteArrayInputStream(internship.getEmbeddableLearningInternship().getReport());
        String fileName = internship.getEmbeddableLearningInternship().getTopic();
        HttpHeaders headers = new HttpHeaders();


        headers.setContentType(MediaType.parseMediaType("application/pdf; charset=UTF-8"));
        String filen = URLEncoder.encode(fileName.replace(" ", "_"), "UTF-8");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "attachment; filename=" + filen +".pdf");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Access-Control-Expose-Headers", "Content-Disposition");

        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
                new InputStreamResource(inputStream), headers, HttpStatus.OK);

        return  response ;
    }

    @GetMapping(value = "/learning-activities/file/{id}", produces = "application/pdf")
    public ResponseEntity<InputStreamResource> learningActivities(@PathVariable Long id) throws UnsupportedEncodingException {

        LearningActivities len = internshipService.getLearningById(id);
        InputStream inputStream = new ByteArrayInputStream(len.getEmbeddableLearningInternship().getReport());
        String fileName = len.getEmbeddableLearningInternship().getTopic();
        HttpHeaders headers = new HttpHeaders();


        headers.setContentType(MediaType.parseMediaType("application/pdf; charset=UTF-8"));
        String filen = URLEncoder.encode(fileName.replace(" ", "_"), "UTF-8");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "attachment; filename=" + filen +".pdf");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Access-Control-Expose-Headers", "Content-Disposition");

        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
                new InputStreamResource(inputStream), headers, HttpStatus.OK);

        return  response ;
    }


}
