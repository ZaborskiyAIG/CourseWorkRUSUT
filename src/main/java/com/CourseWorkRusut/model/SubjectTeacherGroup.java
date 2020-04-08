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

    public long getSubjectTeacherGroupId() {
        return subjectTeacherGroupId;
    }

    public void setSubjectTeacherGroupId(long subjectTeacherGroupId) {
        this.subjectTeacherGroupId = subjectTeacherGroupId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
