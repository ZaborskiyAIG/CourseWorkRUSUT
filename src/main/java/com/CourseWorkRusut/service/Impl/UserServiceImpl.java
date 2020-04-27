package com.CourseWorkRusut.service.Impl;

import com.CourseWorkRusut.DAO.UserDAO;
import com.CourseWorkRusut.DTO.StudentDTO;
import com.CourseWorkRusut.DTO.UserDTO;
import com.CourseWorkRusut.mappers.UserMapper;
import com.CourseWorkRusut.model.Role;
import com.CourseWorkRusut.model.Student;
import com.CourseWorkRusut.model.StudyGroup;
import com.CourseWorkRusut.model.User;
import com.CourseWorkRusut.service.RoleService;
import com.CourseWorkRusut.service.StudentService;
import com.CourseWorkRusut.service.StudyGroupService;
import com.CourseWorkRusut.service.UserService;

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

    private User updateStudent(UserDTO userDTO, User user){  //перенести в сервис студента

        Student student = (Student) userMapper.userDTOToUser(userDTO);

        student.setLogin(user.getLogin());
        student.setPassword(user.getPassword());

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




    @Override
    @Transactional
    public void update(UserDTO userDTO) {   //что происходит, когда из одного метода помеченым @транзакция вызывают другой метод c @транзакция


        User user = userDAO.getUserById(userDTO.getUserId());

        if(user.getClass()==User.class){
            userDAO.delete(user);
        }



        User modifiedUser = null;

        if(userDTO.getClass() == StudentDTO.class) {
          modifiedUser = updateStudent(userDTO, user);
        }

        userDAO.save(modifiedUser);

      //  user.setUserId(userDTO.getUserId());


       // if(user.getClass() == User.class){
                         //сделать метод копирования пропертей

        //    userStudent.setUserId(user.getUserId());
        //    userStudent.setName(userDTO.getName());       //сделать метод по установке апдейта
        //    userStudent.setMiddlename(userDTO.getMiddlename());
        //    userStudent.setSurname(userDTO.getSurname());
        //    userStudent.setEmail(userDTO.getEmail());



         //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

         //   }

    //  if(userDTO.getClass() == StudentDTO.class){
   //       System.out.println("устанавливаем группу");
     //     userStudent.setStudyGroup(studyGroupService.getStudyGroupByNumberGroup(((StudentDTO)userDTO).getNumberGroup()));
     // }

      //  System.out.println("idЖ"+userStudent.getUserId());

    //  userDAO.update (userStudent);
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
