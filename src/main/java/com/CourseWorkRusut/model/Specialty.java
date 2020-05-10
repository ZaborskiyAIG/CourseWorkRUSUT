package com.CourseWorkRusut.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "specialty")
public class Specialty {

    @Id
    @Column(name = "Speciality_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specialtyId;

    @Column(name = "name_speciality")
    private String nameSpecialty;

    @OneToMany(mappedBy = "specialty", fetch=FetchType.LAZY)
    private List<Library> libraries;

    @OneToMany(mappedBy = "specialty", fetch=FetchType.LAZY)
    private List<StudyGroup> studyGroups;

    @Column(name = "amount_semester")
    private int amountSemester;

    public long getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(long specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getNameSpecialty() {
        return nameSpecialty;
    }

    public void setNameSpecialty(String nameSpecialty) {
        this.nameSpecialty = nameSpecialty;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    public List<StudyGroup> getStudyGroups() {
        return studyGroups;
    }

    public void setStudyGroups(List<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }

    public int getAmountSemester() {
        return amountSemester;
    }

    public void setAmountSemester(int amountSemester) {
        this.amountSemester = amountSemester;
    }
}
