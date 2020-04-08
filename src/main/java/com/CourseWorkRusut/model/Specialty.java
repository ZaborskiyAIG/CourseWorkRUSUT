package com.CourseWorkRusut.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "specialty")
public class Specialty {

    @Id
    @Column(name = "Speciality_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long specialtyId;

    @Column(name = "name_speciality")
    private String nameSpecialty;

    @OneToMany(mappedBy = "specialty", fetch=FetchType.LAZY)
    private List<Library> libraries;

    @OneToMany(mappedBy = "specialty", fetch=FetchType.LAZY)
    private List<StudyGroup> studyGroups;

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


}
