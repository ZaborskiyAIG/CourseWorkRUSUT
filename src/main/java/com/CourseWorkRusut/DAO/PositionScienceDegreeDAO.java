package com.CourseWorkRusut.DAO;

import com.CourseWorkRusut.model.Position;
import com.CourseWorkRusut.model.ScienceDegree;

import java.util.List;

public interface PositionScienceDegreeDAO {

    List<String> getAllPositions();

    List<String> getAllScienceDegree();

    List<Position> getPositionsByByName(List<String> namePosition);

    List<ScienceDegree> getScienceDegreeByByName(List<String> nameScienceDegree);
}
