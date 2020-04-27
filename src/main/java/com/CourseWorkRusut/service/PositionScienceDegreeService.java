package com.CourseWorkRusut.service;

import com.CourseWorkRusut.model.Position;
import com.CourseWorkRusut.model.ScienceDegree;

import java.util.List;

public interface PositionScienceDegreeService {

    List<String> getAllPositions();

    List<String> getAllScienceDegree();

    List<Position> getPositionsByByName(List<String> namePosition);

    List<ScienceDegree> getScienceDegreeByByName(List<String> nameScienceDegree);

}
