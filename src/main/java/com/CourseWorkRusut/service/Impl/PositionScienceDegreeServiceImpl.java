package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.PositionScienceDegreeDAO;
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
    public List<Position> getPositionsByByName(List<String> namePosition) {
        return positionScienceDegreeDAO.getPositionsByByName(namePosition);
    }

    @Override
    public List<ScienceDegree> getScienceDegreeByByName(List<String> nameScienceDegree) {
        return  positionScienceDegreeDAO.getScienceDegreeByByName(nameScienceDegree);
    }
}
