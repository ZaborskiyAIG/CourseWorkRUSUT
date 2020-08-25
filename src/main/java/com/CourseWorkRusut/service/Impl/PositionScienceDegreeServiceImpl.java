package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.dao.PositionScienceDegreeDAO;
import com.CourseWorkRusut.model.Position;
import com.CourseWorkRusut.model.ScienceDegree;
import com.CourseWorkRusut.service.PositionScienceDegreeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PositionScienceDegreeServiceImpl implements PositionScienceDegreeService {

    private PositionScienceDegreeDAO positionScienceDegreeDAO;

    @Autowired
    public PositionScienceDegreeServiceImpl(PositionScienceDegreeDAO positionScienceDegreeDAO){
        this.positionScienceDegreeDAO = positionScienceDegreeDAO;
    }

    @Override
    @Transactional
    public List<String> getAllPositions() {
        return positionScienceDegreeDAO.getAllPositions();
    }

    @Override
    @Transactional
    public List<String> getAllScienceDegree() {
     return positionScienceDegreeDAO.getAllScienceDegree();
    }

    @Override
    public List<Position> getPositionsByName(List<String> namePosition) {
        return positionScienceDegreeDAO.getPositionsByName(namePosition);
    }

    @Override
    public List<ScienceDegree> getScienceDegreeByName(List<String> nameScienceDegree) {
        return  positionScienceDegreeDAO.getScienceDegreeByName(nameScienceDegree);
    }
}
