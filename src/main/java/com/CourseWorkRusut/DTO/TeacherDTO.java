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
        this.namePositions = convertPositionsInNamePositions(positions);
        this.nameScienceDegrees = convertScienceDegreesInNameScienceDegrees(scienceDegrees);
        this.scienceDegrees = scienceDegrees;
    }

    public List<PositionDTO> getPositions() {
        return positions;
    }


    public void setNamePositions(List<PositionDTO> positions) {
        this.positions = positions;
        this.namePositions = convertPositionsInNamePositions(positions);
    }

    public List<ScienceDegreeDTO> getScienceDegrees() {
        return scienceDegrees;
    }

    public void setNameScienceDegrees(List<ScienceDegreeDTO> scienceDegrees) {
        this.scienceDegrees = scienceDegrees;
       this.nameScienceDegrees = convertScienceDegreesInNameScienceDegrees(scienceDegrees);
    }

    public List<String> getNamePositions() {
        return namePositions;
    }

    public List<String> getNameScienceDegrees() {
        return nameScienceDegrees;
    }

    private List<String> convertPositionsInNamePositions(List<PositionDTO> positions){ //говнокод, пофиксить
        List<String> namePositions = new ArrayList<>();
        for(PositionDTO positionDTO : positions){
            namePositions.add(positionDTO.getNamePosition());
        }
        return namePositions;
    }

    private List<String> convertScienceDegreesInNameScienceDegrees(List<ScienceDegreeDTO> scienceDegrees){ //говнокод, пофиксить
        List<String> nameScienceDegrees = new ArrayList<>();
        for(ScienceDegreeDTO scienceDegreeDTO : scienceDegrees){
            nameScienceDegrees.add(scienceDegreeDTO.getNameScienceDegree());
        }
        return nameScienceDegrees;
    }

}
