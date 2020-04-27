package com.CourseWorkRusut.service;

import com.CourseWorkRusut.model.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByByName(String nameRole);

    List<String> getAllRoles();

}
