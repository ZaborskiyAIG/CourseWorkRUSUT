package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.UserDAO;
import com.CourseWorkRusut.DTO.StudentDTO;
import com.CourseWorkRusut.DTO.TeacherDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.mappers.UserMapper;
import com.CourseWorkRusut.model.*;
import com.CourseWorkRusut.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserDAO userDAO ;

    private StudyGroupService studyGroupService;

    private UserMapper userMapper;

    @Autowired
    StudentService studentService;

    @Autowired
    RoleService roleService;

    @Autowired
    PositionScienceDegreeService positionScienceDegreeService;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserDAO userDAO, StudyGroupService studyGroupService, UserMapper userMapper){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDAO = userDAO;
        this.studyGroupService = studyGroupService;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public void register(User user) {
        Role role = roleService.getRoleByByName("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(role);
        userDAO.save(user);
    }

    @Transactional
    User updateStudent( Student student){  //перенести в сервис студента
        if(student.getNumberBook() ==null){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String entryYear = student.getEntryDate().format(formatter) ;

            String nameSpecialty = student.getStudyGroup().getSpecialty().getNameSpecialty();

            StudyGroup studyGroup = studyGroupService.getStudyGroupForAddStudent(nameSpecialty, entryYear);
            student.setStudyGroup(studyGroup);

            student.setNumberBook(studentService.generationNumberStudyBook(entryYear, student.getStudyGroup()));

            return student;
        }

        StudyGroup studyGroup = studyGroupService.getStudyGroupByNumberGroup(student.getStudyGroup().getNumberGroup());
        student.setStudyGroup(studyGroup);

        return student;
    }

    @Transactional
    User updateTeacher( Teacher teacher){  //перенести в сервис студента

            List<String> namePositions = new ArrayList<>();
            for(Position position : teacher.getPositions()){
                namePositions.add(position.getNamePosition());
            }

            List<String> nameScienceDegrees = new ArrayList<>();
            for(ScienceDegree scienceDegree : teacher.getScienceDegrees()){
                nameScienceDegrees.add(scienceDegree.getNameScienceDegree());
            }

        teacher.setPositions(positionScienceDegreeService.getPositionsByByName(namePositions));
        teacher.setScienceDegrees(positionScienceDegreeService.getScienceDegreeByByName(nameScienceDegrees));

        return null;
    }

    @Override
    @Transactional
    public User update(UserDTO userDTO) {   //что происходит, когда из одного метода помеченым @транзакция вызывают другой метод c @транзакция

        User user = userDAO.getUserById(userDTO.getUserId());

        User modifiedUser =  userMapper.userDTOToUser(userDTO);
        modifiedUser.setLogin(user.getLogin());
        modifiedUser.setPassword(user.getPassword());

        if(user.getClass()==User.class){
            userDAO.delete(user);
        }

        if(userDTO.getClass() == StudentDTO.class) {
          modifiedUser = updateStudent((Student) user);
        }

        if(userDTO.getClass() == TeacherDTO.class) {
            modifiedUser = updateTeacher((Teacher) user);
        }

        userDAO.save(modifiedUser);
        return modifiedUser;
    }

    @Override
    @Transactional
    public void updateUsers(List<User> users) {

    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);

    }

    @Override
    @Transactional
    public UserDTO getUserById(Long id) {
        User user = userDAO.getUserById(id);
        return userMapper.userToUserDTO(user);

    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    @Transactional
    public List<UserDTO> getAllUser(String offset) {
        return userDAO.getAllUser(offset);
    }

    @Override
    @Transactional
    public List<UserDTO> getStudentsByParameters(String offset, Long groupId, Long specialtyId) {
        return userDAO.getStudentsByParameters(offset, groupId, specialtyId);
    }

    @Override
    @Transactional
    public List<UserDTO> getTeachersByParameters(String offset) {
        List<User> users = userDAO.getTeachersByParameters(offset);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            userDTOS.add(userMapper.userToUserDTO(user));
        }
        return userDTOS;
    }

    @Override
    @Transactional
    public Long contUsers() {
        return userDAO.contUsers();
    }
}
