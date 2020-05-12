package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.ExamDAO;
import com.CourseWorkRusut.DAO.TeacherDAO;
import com.CourseWorkRusut.DTO.ExamGroupDTO;
import com.CourseWorkRusut.DTO.StudentExamDTO;
import com.CourseWorkRusut.model.Exam;
import com.CourseWorkRusut.model.Semester;
import com.CourseWorkRusut.model.StudyGroup;
import com.CourseWorkRusut.service.ExamService;
import com.CourseWorkRusut.service.StudentService;
import com.CourseWorkRusut.service.StudyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamDAO examDAO;

    @Autowired
    private TeacherDAO studyGroupService;

    @Autowired
    private StudentService studentService;

    @Override
    @Transactional
    public void save(ExamGroupDTO exam) {

    }

    @Override
    @Transactional
    public List<ExamGroupDTO> getExamGroup(Long teacherId) {
      List<String> groups = studyGroupService.getNumberGroupByTeacherId(teacherId);

      List<ExamGroupDTO> list = new ArrayList<>();

        for(String group: groups){

            List<String> semesters =  examDAO.getSemesterByGroup(teacherId, group);

            Set<String> set = new HashSet<>(semesters);

            for(String num: set){
                List<String> subjects = examDAO.getSubjectByGroupAndTeacher(teacherId,group);

                for(String subject :subjects){
                    ExamGroupDTO examGroupDTO = new ExamGroupDTO();
                    examGroupDTO.setGroup(group);
                    examGroupDTO.setTypeExam(examDAO.getTypeExamByGroupAndTeacher(teacherId,group, subject, num));
                    examGroupDTO.setSemesters(num);
                    examGroupDTO.setSubject(subject);
                    list.add(examGroupDTO);
                }
            }
        }

    return list;
    }

    @Override
    @Transactional
    public ExamGroupDTO getExamStudents(Long teacherId, String numberGroup, String subject, String semester) {
        ExamGroupDTO examGroupDTO = new ExamGroupDTO();

        examGroupDTO.setSubject(subject);
        examGroupDTO.setGroup(numberGroup);
        examGroupDTO.setSemesters(semester);

        examGroupDTO.setStudents(examDAO.getStudentExamDTO(teacherId, numberGroup, subject, semester) );

        examGroupDTO.setHours(examDAO.getHoursExamByGroupAndTeacher(teacherId,numberGroup, subject, semester));

        examGroupDTO.setTypeExam(examDAO.getTypeExamByGroupAndTeacher(teacherId,numberGroup, subject, semester));

        return examGroupDTO;
    }


    @Override
    @Transactional
    public void saveExamGroup(ExamGroupDTO examGroupDTO, Long teacherId) {  //один селект или много селектов через какое нибудь кэширование

        System.out.println("предмет"+examGroupDTO.getSubject());
        System.out.println("Id"+teacherId);
        System.out.println("id"+examGroupDTO.getGroup());
        System.out.println("семестр"+examGroupDTO.getSemesters());

        List<Exam> exams = examDAO.getExamBySubjectTeacherGroup(examGroupDTO.getSubject(),teacherId, examGroupDTO.getGroup(), examGroupDTO.getSemesters());

        System.out.println("массив:"+exams.size());

        for(Exam exam: exams){
            exam.setHours(examGroupDTO.getHours());
            exam.setTypeExam(examGroupDTO.getTypeExam());

            for(StudentExamDTO dto: examGroupDTO.getStudents()){
                System.out.println("IdExamUS"+exam.getSemester().getStudent().getUserId());
                System.out.println("IdUS"+dto.getUserId());
                if(exam.getSemester().getStudent().getUserId().equals(dto.getUserId())){
                    System.out.println("Работает"+dto.getMark());
                    exam.setMarkExam(dto.getMark());
                    examDAO.update(exam);
                }
            }
          //  exam.setMarkExam();
        }

    }


}
