package com.CourseWorkRusut.model;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "Admin_id")
public class Admin extends User {

  //  @Id
   // @Column(name = "Admin_id")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
   // private long adminId;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    public Admin() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
