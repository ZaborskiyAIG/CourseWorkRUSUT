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
                    examGroupDTO.setTypeExam(examDAO.getTypeExamByGroupAndTeacher(teacherId,group));
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
    public ExamGroupDTO getExamStudents(Long teacherId, String numberGroup) {
        ExamGroupDTO examGroupDTO = new ExamGroupDTO();

        examGroupDTO.setGroup(numberGroup);
      //  examGroupDTO.setSubject(examDAO.getSubjectByGroupAndTeacher(teacherId,numberGroup));
      //  examGroupDTO.setTypeExam(examDAO.getTypeExamByGroupAndTeacher(teacherId,numberGroup));

        examGroupDTO.setStudents(studentService.getStudentsByNumberGroupAndSubject(numberGroup,examGroupDTO.getSubject()));

        return examGroupDTO;
    }


    @Override
    public void saveExamGroup(ExamGroupDTO examGroupDTO, Long teacherId) {

        List<Exam> exams = examDAO.getExamBySubjectTeacherGroup(examGroupDTO.getSubject(),teacherId, examGroupDTO.getGroup(), examGroupDTO.getSemesters());


        for(Exam exam: exams){
            exam.setHours(examGroupDTO.getHours());
            exam.setTypeExam(examGroupDTO.getTypeExam());


            for(StudentExamDTO dto: examGroupDTO.getStudents()){
                if(exam.getSemester().getStudent().getUserId().equals(dto.getUserId())){
                    exam.setMarkExam(dto.getMark());
                    examDAO.update(exam);
                }
            }
          //  exam.setMarkExam();
        }

    }


}
