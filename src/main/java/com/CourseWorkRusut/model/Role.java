package com.CourseWorkRusut.model;


import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role")
    private String nameRole;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String role) {
        this.nameRole = role;
    }
}
