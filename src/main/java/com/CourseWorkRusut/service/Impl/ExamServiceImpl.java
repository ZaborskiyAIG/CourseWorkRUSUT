package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.dao.ExamDAO;
import com.CourseWorkRusut.dao.TeacherDAO;
import com.CourseWorkRusut.dto.ExamGroupDTO;
import com.CourseWorkRusut.dto.StudentExamDTO;
import com.CourseWorkRusut.dto.StudentExamsDTO;
import com.CourseWorkRusut.model.Exam;
import com.CourseWorkRusut.model.Semester;
import com.CourseWorkRusut.service.ExamService;
import com.CourseWorkRusut.service.StudentService;
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
                List<String> subjects = examDAO.getSubjectByGroupAndTeacher(teacherId,group, num);

                for(String subject :subjects){
                    ExamGroupDTO examGroupDTO = new ExamGroupDTO();
                    examGroupDTO.setGroup(group);
                    examGroupDTO.setTypeExam(examDAO.getTypeExamByGroupAndTeacher(teacherId,group, subject, num));
                    examGroupDTO.setSemester(num);
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
        examGroupDTO.setSemester(semester);
        examGroupDTO.setStudents(examDAO.getStudentExamDTO(teacherId, numberGroup, subject, semester) );
        examGroupDTO.setHours(examDAO.getHoursExamByGroupAndTeacher(teacherId,numberGroup, subject, semester));
        examGroupDTO.setTypeExam(examDAO.getTypeExamByGroupAndTeacher(teacherId,numberGroup, subject, semester));

        return examGroupDTO;
    }


    @Override
    @Transactional
    public void saveExamGroup(ExamGroupDTO examGroupDTO, Long teacherId) {

        List<Exam> exams = examDAO.getExamBySubjectTeacherGroup(examGroupDTO.getSubject(),teacherId, examGroupDTO.getGroup(), examGroupDTO.getSemester());

        for(Exam exam: exams){
            exam.setHours(examGroupDTO.getHours());
            exam.setTypeExam(examGroupDTO.getTypeExam());
            for(StudentExamDTO dto: examGroupDTO.getStudents()){
                if(exam.getSemester().getStudent().getUserId().equals(dto.getUserId())){
                    exam.setMarkExam(dto.getMark());
                    examDAO.update(exam);
                }
            }
        }
    }

    @Override
    @Transactional
    public List<StudentExamsDTO> getStudentExams(Long id, String semester) {
        return examDAO.getStudentExams(id, semester);
    }

    @Override
    @Transactional
    public Semester getSemesterByIdStudentAndNumber(Long id, String semester) {
        return examDAO.getSemesterByIdStudentAndNumber(id, semester);
    }


}
