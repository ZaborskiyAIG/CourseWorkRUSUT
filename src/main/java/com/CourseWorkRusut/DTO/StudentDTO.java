package com.CourseWorkRusut.DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StudentDTO extends UserDTO {

    private Long numberBook;

    private String numberGroup;

    private String nameSpecialty;

    private String entryDate;

    public StudentDTO(Long userId,  //некрасиво, пофиксить бы
                      String name,
                      String surname,
                      String midlename,
                      String email,
                      Long numberBook,
                      String numberGroup,
                      String nameSpecialty,
                      LocalDate entryDate) {
        super(userId,name,surname,midlename,email);
        this.numberBook = numberBook;
        this.numberGroup = numberGroup;
        this.nameSpecialty = nameSpecialty;
        this.entryDate = convertLocalDateToString(entryDate);
    }

    public Long getNumberBook() {
        return numberBook;
    }

    public void setNumberBook(Long numberBook) {
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

    private String convertLocalDateToString(LocalDate entryDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return entryDate.format(formatter);
    }
}
