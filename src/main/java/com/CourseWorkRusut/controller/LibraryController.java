package com.CourseWorkRusut.controller;

import com.CourseWorkRusut.DTO.LibraryCounterDTO;
import com.CourseWorkRusut.model.Library;
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

    @GetMapping(value = "/library")
    public ResponseEntity<LibraryCounterDTO> library(@RequestParam(value = "offset", defaultValue = "0" )String offset)  {
        return new ResponseEntity<>(libraryService.getAllLibrary(offset), HttpStatus.OK);
    }


}
