package com.CourseWorkRusut.model;

import javax.persistence.*;

@Entity
@Table(name = "subject_teacher_group")
public class SubjectTeacherGroup {

    @Id
    @Column(name = "subject_teacher_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subjectTeacherGroupId;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private StudyGroup studyGroup;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

}
