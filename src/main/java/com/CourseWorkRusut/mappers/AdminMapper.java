package com.CourseWorkRusut.mappers;

import com.CourseWorkRusut.dto.AdminDTO;
import com.CourseWorkRusut.model.Admin;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminDTO adminToAdminDTO(Admin admin);

    Admin adminDTOToAdmin(AdminDTO dto);

}
