package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.SpecialtyDAO;
import com.CourseWorkRusut.DAO.StudentDAO;
import com.CourseWorkRusut.DAO.StudyGroupDAO;
import com.CourseWorkRusut.model.Specialty;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.StudyGroup;
import com.CourseWorkRusut.service.SpecialtyService;
import com.CourseWorkRusut.service.StudyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class StudyGroupServiceImpl implements StudyGroupService {

    private final StudyGroupDAO studyGroupDAO;

    private final SpecialtyDAO specialtyDAO;

    @Autowired
    public StudyGroupServiceImpl(StudyGroupDAO studyGroupDAO, SpecialtyDAO specialtyDAO) {
        this.studyGroupDAO = studyGroupDAO;
        this.specialtyDAO = specialtyDAO;
    }

    @Override
    @Transactional
    public StudyGroup getStudyGroup(long specialtyId, String entryYear){
        List<StudyGroup> studyGroups =  studyGroupDAO.getAllStudyGroupBySpecialty(specialtyId);

        StudyGroup studyGroup;

        Iterator<StudyGroup> iter = studyGroups.iterator();  //чекнуть iterator vs listIterator разницу и for vs iterator, что лушче

        while(iter.hasNext()){
            studyGroup = iter.next();
            if(studyGroupDAO.getCountStudentsInGroup(studyGroup)<=25){ //25 максимальное число учеников в группе, надо пофиксить, но пока так
            return studyGroup;
            }
        }
        Specialty specialty =specialtyDAO.getSpecialtyById(specialtyId);
        long idNewStudyGroup =  studyGroupDAO.addStudyGroup(new StudyGroup( generatedNumberGroup(specialtyId,entryYear,studyGroups.size()+1 ),specialty));


        return studyGroupDAO.getStudyGroupById(idNewStudyGroup) ;
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
