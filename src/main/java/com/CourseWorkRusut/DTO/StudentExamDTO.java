package com.CourseWorkRusut.DTO;

import java.util.List;

public class StudentExamDTO {

    private Long userId;

    private String mark;

    private String nameStudents;

    private String numberBook;

    public StudentExamDTO(Long userId, String name, String Surname, String middlename, String numberBook) {

        if(middlename==null){  //исправить
            middlename = " ";
        }

        this.nameStudents = name +" "+Surname+" "+middlename;
        this.numberBook = numberBook;
        this.userId = userId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public StudentExamDTO() {
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getNameStudents() {
        return nameStudents;
    }

    public void setNameStudents(String nameStudents) {
        this.nameStudents = nameStudents;
    }

    public String getNumberBook() {
        return numberBook;
    }

    public void setNumberBook(String numberBook) {
        this.numberBook = numberBook;
    }
}
