package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.model.StudyGroup;
import com.CourseWorkRusut.service.StudentService;
import com.CourseWorkRusut.service.StudyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {


    private StudyGroupService studyGroupService;

    @Autowired
    public StudentServiceImpl( StudyGroupService studyGroupService) {
        this.studyGroupService = studyGroupService;
    }


    @Override
    @Transactional
    public String generationNumberStudyBook(String entryDate, StudyGroup studyGroup) {

        System.out.println("А?"+entryDate);

        Long number = studyGroupService.getCountStudentsInGroup(studyGroup) +1;

        String numberStudent = number < 10 ? "0"+number : String.valueOf(number);

        String numberStudyBook = entryDate.substring(entryDate.length()-4) +
                "0" +
                studyGroup.getNumberGroup().charAt(studyGroup.getNumberGroup().length()-1) +
                numberStudent;
        return numberStudyBook;

    }
}
