package com.CourseWorkRusut.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teacher")
@PrimaryKeyJoinColumn(name = "Teacher_id")
public class Teacher extends User {

  //  @Id
 //   @Column(name = "Teacher_id")
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
  //  private long teacherId;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_science_degree",
            joinColumns = { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "position_id")})
    private List<Position> positions;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_science_degree",
            joinColumns = { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "science_degree")})
    private List<ScienceDegree> scienceDegrees;

    @OneToMany(mappedBy = "teacher")
    private List<SubjectTeacherGroup> subjectTeacherGroups;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
