package com.CourseWorkRusut.mappers;

import com.CourseWorkRusut.DTO.PositionDTO;
import com.CourseWorkRusut.model.Position;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PositionMapper {

    PositionDTO positionToPositionDTO(Position position);

    Position positionDTOToPosition(PositionDTO dto);
}
