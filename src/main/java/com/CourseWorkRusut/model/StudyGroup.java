package com.CourseWorkRusut.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "study_group")
public class StudyGroup {

    @Id
    @Column(name = "study_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groupId;

    @Column(name = "group_number")
    private String numberGroup;

    @OneToMany(mappedBy = "studyGroup")
    List<Student> students;

    @OneToMany(mappedBy = "studyGroup" )
    List<Subject> subjects;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Specialty specialty;

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
}
