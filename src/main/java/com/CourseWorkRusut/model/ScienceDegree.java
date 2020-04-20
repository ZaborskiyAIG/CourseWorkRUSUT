package com.CourseWorkRusut.model;

import javax.persistence.*;

@Entity
@Table(name = "science_degree")
public class ScienceDegree {

    @Id
    @Column(name = "science_degree_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scienceDegreeId;

    @Column(name = "name_science_degree")
    private String nameScienceDegree;

    public Long getScienceDegreeId() {
        return scienceDegreeId;
    }

    public void setScienceDegreeId(Long scienceDegreeId) {
       this.scienceDegreeId = scienceDegreeId;
    }

    public String getNameScienceDegree() {
        return nameScienceDegree;
    }

    public void setNameScienceDegree(String nameScienceDegree) {
        this.nameScienceDegree = nameScienceDegree;
    }
}
