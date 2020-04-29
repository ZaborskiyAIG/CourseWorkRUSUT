package com.CourseWorkRusut.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StudentDTO extends UserDTO {

    private String numberBook;

    private String numberGroup;

    private String nameSpecialty;

    private String entryDate;

    @JsonIgnore
    private LocalDate date;

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
        this.entryDate = convertLocalDateToString(entryDate);
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

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = convertLocalDateToString(entryDate);
    }

    private String convertLocalDateToString(LocalDate entryDate){  //сделать декоратор, перенести это в другое место
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return entryDate.format(formatter);
    }
}
