package com.CourseWorkRusut.mappers;

import com.CourseWorkRusut.DTO.StudentDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mappings({
            @Mapping(target="numberGroup", source = "student.studyGroup.numberGroup"),
            @Mapping(target="nameSpecialty", source = "student.studyGroup.specialty.nameSpecialty")})
    StudentDTO studentToStudentDTO(Student student);


    @Mappings({
            @Mapping(target="studyGroup.numberGroup", source = "dto.numberGroup"),
            @Mapping(target="studyGroup.specialty.nameSpecialty", source = "dto.nameSpecialty")})
    Student studentDTOToStudent(StudentDTO dto);


}
