package com.CourseWorkRusut.mappers;

import com.CourseWorkRusut.DTO.ScienceDegreeDTO;
import com.CourseWorkRusut.model.ScienceDegree;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScienceDegreeMapper {

    ScienceDegreeDTO scienceDegreeToScienceDegreeDTO(ScienceDegree scienceDegree);

    ScienceDegree ScienceDegreeDTOToScienceDegree(ScienceDegreeDTO dto);
}
