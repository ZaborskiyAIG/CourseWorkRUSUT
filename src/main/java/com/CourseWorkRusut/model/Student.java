package com.CourseWorkRusut.model;

import javax.persistence.*;
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

    @Column(name = "entry_year")
    private int entryYear;

    @ManyToOne
    @JoinColumn (name="Group_id")
    private StudyGroup studyGroup;

    @OneToMany(mappedBy = "student", fetch=FetchType.LAZY)
    private List<Semester> semester;




    public String getNumberBook() {
        return numberBook;
    }

    public void setNumberBook(String numberBook) {
        this.numberBook = numberBook;
    }

    public int getEntryYear() {
        return entryYear;
    }

    public void setEntryYear(int entryYear) {
        this.entryYear = entryYear;
    }
}
