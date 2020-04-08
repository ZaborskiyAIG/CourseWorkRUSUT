package com.CourseWorkRusut.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "Admin_id")
public class Admin extends User {

  //  @Id
   // @Column(name = "Admin_id")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
   // private long adminId;

    @Transient
    private final String nameRole = "ROLE_ADMIN";

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(nameRole));
        return list;
    }
}
