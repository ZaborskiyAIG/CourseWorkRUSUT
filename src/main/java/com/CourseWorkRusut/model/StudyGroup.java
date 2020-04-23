package com.CourseWorkRusut.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "study_group")
public class StudyGroup {

    @Id
    @Column(name = "Group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(name = "group_number")
    private String numberGroup;

    @JsonIgnore
    @OneToMany(mappedBy = "studyGroup", fetch=FetchType.LAZY)
    private List<Student> students;

    @JsonIgnore
    @OneToMany(mappedBy = "studyGroup", fetch=FetchType.LAZY )
    private List<SubjectTeacherGroup> subjectTeacherGroups;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Specialty specialty;

    public StudyGroup(){

    }

    public StudyGroup(String numberGroup,Specialty specialty){
        this.numberGroup=numberGroup;
        this.specialty=specialty;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getNumberGroup() {
        return numberGroup;
    }

    public void setNumberGroup(String numberGroup) {
        this.numberGroup = numberGroup;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<SubjectTeacherGroup> getSubjectTeacherGroups() {
        return subjectTeacherGroups;
    }

    public void setSubjectTeacherGroups(List<SubjectTeacherGroup> subjectTeacherGroups) {
        this.subjectTeacherGroups = subjectTeacherGroups;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
