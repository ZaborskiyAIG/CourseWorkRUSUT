package com.CourseWorkRusut.mappers;

import com.CourseWorkRusut.dto.UserDTO;
import com.CourseWorkRusut.model.User;


public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO dto);

}
