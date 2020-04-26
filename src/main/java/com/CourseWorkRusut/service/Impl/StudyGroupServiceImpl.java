package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.StudyGroupDAO;
import com.CourseWorkRusut.model.Specialty;
import com.CourseWorkRusut.model.StudyGroup;
import com.CourseWorkRusut.service.SpecialtyService;
import com.CourseWorkRusut.service.StudyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
public class StudyGroupServiceImpl implements StudyGroupService {

    private final StudyGroupDAO studyGroupDAO;

    private final SpecialtyService specialtyService;

    @Autowired
    public StudyGroupServiceImpl(StudyGroupDAO studyGroupDAO, SpecialtyService specialtyService) {
        this.studyGroupDAO = studyGroupDAO;
        this.specialtyService = specialtyService;
    }

    @Override
    @Transactional
    public StudyGroup getStudyGroupByNumberGroup(String numberGroup) {
        return studyGroupDAO.getStudyGroupByNumberGroup(numberGroup);
    }

    public Long getCountStudentsInGroup(StudyGroup studyGroup){
        return studyGroupDAO.getCountStudentsInGroup(studyGroup.getNumberGroup());
    }

    @Override
    @Transactional
    public StudyGroup getStudyGroupForAddStudent(String nameSpecialty, String entryYear){
        List<StudyGroup> studyGroups =  studyGroupDAO.getAllStudyGroupByNameSpecialty(nameSpecialty);

        StudyGroup studyGroup;

        Iterator<StudyGroup> iter = studyGroups.iterator();  //чекнуть iterator vs listIterator разницу и for vs iterator, что лушче

        while(iter.hasNext()){
            studyGroup = iter.next();
            if(studyGroupDAO.getCountStudentsInGroup(studyGroup.getNumberGroup())<=25){ //25 максимальное число учеников в группе, надо пофиксить, но пока так
            return studyGroup;
            }
        }

        Specialty specialty = specialtyService.getSpecialtyByName(nameSpecialty);

        Long generationIdNewStudyGroup =  studyGroupDAO.addStudyGroup(new StudyGroup( generatedNumberGroup(specialty.getSpecialtyId(),entryYear,studyGroups.size()+1 ), specialty));


        return studyGroupDAO.getStudyGroupById(generationIdNewStudyGroup) ;
    }



    //группа будет генериться так *#-*порядковый_номер_группы* , где * это id специальности, #-последняя цифра этого года
    private String generatedNumberGroup(long specialtyId,String entryYear, int lastNumberGroup){   //над подумать как лучше сделать
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(specialtyId)
                .append(entryYear.charAt(entryYear.length()-1))
                .append("-")
                .append(lastNumberGroup);

        return stringBuilder.toString();
    }

}
