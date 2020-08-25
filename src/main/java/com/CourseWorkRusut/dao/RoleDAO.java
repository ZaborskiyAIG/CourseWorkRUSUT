package com.CourseWorkRusut.dao;

import com.CourseWorkRusut.model.Role;

import java.util.List;

public interface RoleDAO {

    Role getRoleByByName(String nameRole);

    List<String> getAllRoles();

}
