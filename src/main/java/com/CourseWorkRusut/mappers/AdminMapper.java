package com.CourseWorkRusut.mappers;

import com.CourseWorkRusut.DTO.AdminDTO;
import com.CourseWorkRusut.model.Admin;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminDTO adminToAdminDTO(Admin position);

    Admin adminDTOToAdmin(AdminDTO dto);

}
