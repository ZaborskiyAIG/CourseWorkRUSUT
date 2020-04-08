package com.CourseWorkRusut.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @Column(name = "Subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subjectId;

    @Column(name = "name_subject")
    private String nameSubject;

    @OneToMany(mappedBy = "subject", fetch=FetchType.LAZY)
    private List<Exam> exams;

    @OneToMany(mappedBy = "subject", fetch=FetchType.LAZY)
    private List<SubjectTeacherGroup> subjectTeacherGroups;


    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }
}
