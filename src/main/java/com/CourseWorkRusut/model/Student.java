package com.CourseWorkRusut.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "Student_id")
public class Student extends User {

   // @Id
   // @Column(name = "Student_id")
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
  //  private long studentId;

    @Transient
    private final String nameRole = "ROLE_STUDENT";

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(nameRole));
        return list;
    }
}
