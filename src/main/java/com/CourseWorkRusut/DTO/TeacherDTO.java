package com.CourseWorkRusut.DTO;

import java.util.ArrayList;
import java.util.List;

public class TeacherDTO extends UserDTO {

    private List<String> namePositions = new ArrayList<>();

    private List<String> nameScienceDegrees = new ArrayList<>();

    public TeacherDTO() {
    }

    public List<String> getNamePositions() {
        return namePositions;
    }

    public List<String> getNameScienceDegrees() {
        return nameScienceDegrees;
    }

    public void setNamePositions(List<PositionDTO> namePositions) {
        for(PositionDTO positionDTO : namePositions){
            this.namePositions.add(positionDTO.getNamePosition());
        }
    }

    public void setNameScienceDegrees(List<ScienceDegreeDTO> nameScienceDegrees) {
        for (ScienceDegreeDTO scienceDegreeDTO : nameScienceDegrees) {
            this.nameScienceDegrees.add(scienceDegreeDTO.getNameScienceDegree());
        }
    }


}
