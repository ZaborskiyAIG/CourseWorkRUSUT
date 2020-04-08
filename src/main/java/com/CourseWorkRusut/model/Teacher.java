package com.CourseWorkRusut.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "teacher")
@PrimaryKeyJoinColumn(name = "Teacher_id")
public class Teacher extends User {


  //  @Id
 //   @Column(name = "Teacher_id")
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
  //  private long teacherId;
    @Transient
    private final String nameRole = "ROLE_TEACHER";

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
            inverseJoinColumns = { @JoinColumn(name = "science_degree_id")})
    private List<ScienceDegree> scienceDegrees;

    @OneToMany(mappedBy = "teacher")
    private List<SubjectTeacherGroup> subjectTeacherGroups;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(nameRole));
        return list;
    }
}
