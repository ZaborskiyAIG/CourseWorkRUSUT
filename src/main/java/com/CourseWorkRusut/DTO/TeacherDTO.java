package com.CourseWorkRusut.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class TeacherDTO extends UserDTO {

    private List<String> namePositions = new ArrayList<>();

    private List<String> nameScienceDegrees = new ArrayList<>();

    @JsonIgnore
    private List<PositionDTO> positions = new ArrayList<>();

    @JsonIgnore
    private List<ScienceDegreeDTO> scienceDegrees = new ArrayList<>();

    public TeacherDTO() {
    }

    public TeacherDTO(List<PositionDTO> positions, List<ScienceDegreeDTO> scienceDegrees) {
        this.positions = positions;
        this.namePositions = convertPositionsToNamePositions(positions);
        this.nameScienceDegrees = convertScienceDegreesToNameScienceDegrees(scienceDegrees);  //удалить конвертеры, изменить запросы к базе
        this.scienceDegrees = scienceDegrees;
    }

    public List<PositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionDTO> positions) {
        this.positions = positions;
    }

    public List<ScienceDegreeDTO> getScienceDegrees() {
        return scienceDegrees;
    }

    public void setScienceDegrees(List<ScienceDegreeDTO> scienceDegrees) {
        this.scienceDegrees = scienceDegrees;
    }

    public void setNamePositions(List<PositionDTO> positions) {
        this.positions = positions;
        this.namePositions = convertPositionsToNamePositions(positions);
    }



    public void setNameScienceDegrees(List<ScienceDegreeDTO> scienceDegrees) {
        this.scienceDegrees = scienceDegrees;
       this.nameScienceDegrees = convertScienceDegreesToNameScienceDegrees(scienceDegrees);
    }

    public List<String> getNamePositions() {
        return namePositions;
    }

    public List<String> getNameScienceDegrees() {
        return nameScienceDegrees;
    }

    private List<String> convertPositionsToNamePositions(List<PositionDTO> positions){ //говнокод, пофиксить, чекнуть декоратор
        List<String> namePositions = new ArrayList<>();
        for(PositionDTO positionDTO : positions){
            namePositions.add(positionDTO.getNamePosition());
        }
        return namePositions;
    }

    private List<String> convertScienceDegreesToNameScienceDegrees(List<ScienceDegreeDTO> scienceDegrees){ //говнокод, пофиксить
        List<String> nameScienceDegrees = new ArrayList<>();
        for(ScienceDegreeDTO scienceDegreeDTO : scienceDegrees){
            nameScienceDegrees.add(scienceDegreeDTO.getNameScienceDegree());
        }
        return nameScienceDegrees;
    }

}
