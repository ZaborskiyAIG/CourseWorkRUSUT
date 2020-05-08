package com.CourseWorkRusut.service;

import com.CourseWorkRusut.model.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByName(String nameRole);

    List<String> getAllRoles();

}
