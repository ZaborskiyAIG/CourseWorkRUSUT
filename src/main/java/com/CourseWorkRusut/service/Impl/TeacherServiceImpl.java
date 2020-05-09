package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.PositionScienceDegreeDAO;
import com.CourseWorkRusut.DAO.TeacherDAO;
import com.CourseWorkRusut.DAO.UserDAO;
import com.CourseWorkRusut.DTO.*;
import com.CourseWorkRusut.mappers.UserMapper;
import com.CourseWorkRusut.model.*;
import com.CourseWorkRusut.service.StudyGroupService;
import com.CourseWorkRusut.service.SubjectService;
import com.CourseWorkRusut.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TeacherServiceImpl implements TeacherService {

    private PositionScienceDegreeDAO positionScienceDegreeService;

    private UserMapper userMapper;

    @Autowired
    private StudyGroupService studyGroupService;

    @Autowired
    private SubjectService subjectService;

    private TeacherDAO teacherDAO;

    @Autowired
    public TeacherServiceImpl(PositionScienceDegreeDAO positionScienceDegreeService, UserMapper userMapper, TeacherDAO teacherDAO){
        this.positionScienceDegreeService =  positionScienceDegreeService;
        this.userMapper = userMapper;
        this.teacherDAO = teacherDAO;
    }

    @Transactional
    public User updateTeacher(Teacher teacher, List<SubjectTeacherGroupDTO> stg){

        List<String> namePositions = new ArrayList<>();
        for(Position position : teacher.getPositions()){
            namePositions.add(position.getNamePosition());
        }

        List<String> nameScienceDegrees = new ArrayList<>();
        for(ScienceDegree scienceDegree : teacher.getScienceDegrees()){
            nameScienceDegrees.add(scienceDegree.getNameScienceDegree());
        }

        teacher.setPositions(new HashSet<>(positionScienceDegreeService.getPositionsByName(namePositions)));
        teacher.setScienceDegrees(new HashSet<>(positionScienceDegreeService.getScienceDegreeByName(nameScienceDegrees)));

        List<SubjectTeacherGroup> subjectTeacherGroups = new ArrayList<>();

        if(stg.size()==0){
          List<SubjectTeacherGroup> list =  teacherDAO.getSTGByTeacherId(teacher.getUserId());

          for(SubjectTeacherGroup ss: list){
              teacherDAO.deleteSubjectTeacherGroup(ss);
          }

        }else {

            for (SubjectTeacherGroupDTO ss : stg) {

                for (String str : ss.getGroups()) {
                    SubjectTeacherGroup s = new SubjectTeacherGroup();
                    StudyGroup studyGroup = studyGroupService.getStudyGroupByNumberGroup(str);
                    s.setStudyGroup(studyGroup);
                    s.setSubject(subjectService.getSubjectByName(ss.getSubject()));
                    s.setTeacher(teacher);
                    teacherDAO.saveSubjectTeacherGroup(s);

                    subjectTeacherGroups.add(s);
                }


            }
        }
        teacher.setSubjectTeacherGroups(subjectTeacherGroups);

        return teacher;
    }




    @Override
    @Transactional
    public UserCounterDTO getTeachersByParameters(String offset,String position, String degree) {
        List<User> users = teacherDAO.getTeachersByParameters(offset, position, degree);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            userDTOS.add(userMapper.userToUserDTO(user));
        }

        Long counter = teacherDAO.counterTeachersByParameters(position, degree);

        return new UserCounterDTO(userDTOS,counter);
    }


    @Override
    @Transactional
    public List<SubjectTeacherGroupDTO> getSubjectTeacherGroupDTO(Long teacherId) {
        List<SubjectTeacherGroupDTO> list = new ArrayList<>();

        Set<Subject> subjects = new HashSet<>(subjectService.getSubjectByTeacher(teacherId));

        for(Subject sb : subjects){
            SubjectTeacherGroupDTO subjectTeacherGroupDTO = new SubjectTeacherGroupDTO();

            List<StudyGroup> studyGroups = studyGroupService.getStudyGroupBySubject(sb.getNameSubject());


            List<String> numberStudyGroup = new ArrayList<>();
            for(StudyGroup StudyGroup : studyGroups){
                numberStudyGroup.add(StudyGroup.getNumberGroup());
            }

            subjectTeacherGroupDTO.setGroups(numberStudyGroup);
            subjectTeacherGroupDTO.setSubject(sb.getNameSubject());
            list.add(subjectTeacherGroupDTO);
        }
        return list;
    }

//    @Override
//    @Transactional
//    public void deleteSubjectTeacherGroup(List<SubjectTeacherGroupDTO> subjectTeacherGroupDTO) {
//
//        for(SubjectTeacherGroupDTO dto: subjectTeacherGroupDTO) {
//            List<SubjectTeacherGroup> subjectTeacherGroup = teacherDAO.getSubjectTeacherGroupByNumberGroupBySubject(dto.getGroups(),dto.getSubject() );
//
//               for(SubjectTeacherGroup stg:subjectTeacherGroup)
//                   teacherDAO.deleteSubjectTeacherGroup(stg);
//        }
//    }


//    public List<ExamGroupDTO> getExamGroup(Long teacherId){
//
//    //    List<ExamDTO> list = teacherDAO.getExamBy
//
//        return null;
//    }


}
