package com.CourseWorkRusut.mappers;

import com.CourseWorkRusut.dto.TeacherDTO;
import com.CourseWorkRusut.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ScienceDegreeMapper.class, PositionMapper.class})
public interface TeacherMapper {

    @Mappings({
            @Mapping(target="namePositions", source = "teacher.positions"),
            @Mapping(target="nameScienceDegrees", source = "teacher.scienceDegrees")})
    TeacherDTO teacherToTeacherDTO(Teacher teacher);


    @Mappings({
            @Mapping(target="positions", source = "dto.positions"),
            @Mapping(target="scienceDegrees", source = "dto.scienceDegrees")})
    Teacher teacherDTOToTeacher(TeacherDTO dto);

}
