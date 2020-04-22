package com.CourseWorkRusut.DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StudentDTO extends UserDTO {

    private Long numberBook;

    private String numberGroup;

    private String nameSpecialty;

    private String entryDate;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.entryDate = entryDate.format(formatter);
    }
}
