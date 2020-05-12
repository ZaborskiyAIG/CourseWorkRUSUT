package com.CourseWorkRusut.DTO;

import java.util.List;

public class StudentExamDTO {

    private Long userId;

    private String mark;

    private String nameStudent;

    private String numberBook;

    public StudentExamDTO(Long userId, String name, String Surname, String middlename, String numberBook, String mark) {

        if(middlename==null){  //исправить
            middlename = " ";
        }

        this.nameStudent = name +" "+Surname+" "+middlename;
        this.numberBook = numberBook;
        this.userId = userId;
        this.mark = mark;
    }

    public StudentExamDTO(Long userId, String name, String Surname, String middlename, String numberBook) {

        if(middlename==null){  //исправить
            middlename = " ";
        }

        this.nameStudent = name +" "+Surname+" "+middlename;
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

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudents) {
        this.nameStudent = nameStudents;
    }

    public String getNumberBook() {
        return numberBook;
    }

    public void setNumberBook(String numberBook) {
        this.numberBook = numberBook;
    }

}
