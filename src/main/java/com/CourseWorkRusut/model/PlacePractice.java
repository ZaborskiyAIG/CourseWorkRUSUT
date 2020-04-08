package com.CourseWorkRusut.model;

import javax.persistence.*;

@Entity
@Table(name = "place_practice")
public class PlacePractice {

    @Id
    @Column(name = "Place_practice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long placePracticeId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "address")
    private String address;

    @Column(name = "internship_director")
    private String internshipDirector;

    @Column(name = "phone")
    private String phone;

    public long getPlacePracticeId() {
        return placePracticeId;
    }

    public void setPlacePracticeId(long placePracticeId) {
        this.placePracticeId = placePracticeId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInternshipDirector() {
        return internshipDirector;
    }

    public void setInternshipDirector(String internshipDirector) {
        this.internshipDirector = internshipDirector;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
