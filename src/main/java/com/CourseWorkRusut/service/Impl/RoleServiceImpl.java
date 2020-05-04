package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.RoleDAO;
import com.CourseWorkRusut.model.Role;
import com.CourseWorkRusut.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO){
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional
    public Role getRoleByName(String nameRole) {
        return roleDAO.getRoleByByName(nameRole);
    }

    @Override
    @Transactional
    public List<String> getAllRoles() {
       return roleDAO.getAllRoles();
    }
}
