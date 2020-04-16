package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.StudentDAO;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.StudyGroup;
import com.CourseWorkRusut.service.StudentService;
import com.CourseWorkRusut.service.StudyGroupService;
import com.CourseWorkRusut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDAO studentDAO;

    private StudyGroupService studyGroupService;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }


    @Override
    @Transactional
    public void generationNumberRecordBook(long specialtyId) {
        List<Student> students =  studentDAO.getAllStudentWithoutRecordNumber();

        System.out.println("///"+students.size());

        for (Student student : students) {
            student.setStudyGroup(studyGroupService.getStudyGroup(specialtyId, String.valueOf(student.getEntryYear().getYear())));
            //System.out.println(iterator.next());
        }


    }
}
