package com.CourseWorkRusut.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "Student_id")
public class Student extends User {

   // @Id
   // @Column(name = "Student_id")
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
  //  private long studentId;

    @Column(name = "number_book")
    private String numberBook;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "entry_year")
    private LocalDate entryDate = LocalDate.now();

    @ManyToOne
    @JoinColumn (name="Group_id")
    private StudyGroup studyGroup;

    @JsonIgnore
    @OneToMany(mappedBy = "student", fetch=FetchType.LAZY)
    private List<Semester> semester;

    public String getNumberBook() {
        return numberBook;
    }

    public void setNumberBook(String numberBook) {
        this.numberBook = numberBook;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public List<Semester> getSemester() {
        return semester;
    }

    public void setSemester(List<Semester> semester) {
        this.semester = semester;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }


}
