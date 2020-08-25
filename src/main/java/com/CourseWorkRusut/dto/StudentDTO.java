package com.CourseWorkRusut.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StudentDTO extends UserDTO {

    private String numberBook;

    private String numberGroup;

    private String nameSpecialty;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate entryDate;

    public StudentDTO(Long userId,  //некрасиво, пофиксить бы
                      String name,
                      String surname,
                      String middlename,
                      String email,
                      String numberBook,
                      String numberGroup,
                      String nameSpecialty,
                      LocalDate entryDate,
                      String nameRole) {
        super(userId,name,surname,middlename,email, nameRole);

        this.numberBook = numberBook;
        this.numberGroup = numberGroup;
        this.nameSpecialty = nameSpecialty;
        this.entryDate = entryDate;
    }

    public StudentDTO(Long userId,
                      String name,
                      String surname,
                      String middlename,
                      String numberBook) {
        super(userId,name,surname,middlename);

        this.numberBook = numberBook;
    }

    public StudentDTO(){
    }

    public String getNumberBook() {
        return numberBook;
    }

    public void setNumberBook(String numberBook) {
        this.numberBook = numberBook;
    }

    public String getNumberGroup() {
        return numberGroup;
    }

    public void setNumberGroup(String numberGroup) {
        this.numberGroup = numberGroup;
    }

    public String getNameSpecialty() {
        return nameSpecialty;
    }

    public void setNameSpecialty(String nameSpecialty) {
        this.nameSpecialty = nameSpecialty;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

}
