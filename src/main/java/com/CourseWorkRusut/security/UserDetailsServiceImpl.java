package com.CourseWorkRusut.security;

import com.CourseWorkRusut.model.User;
import com.CourseWorkRusut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.getUserByLogin(login);
        System.out.println("retur user");
        System.out.println(user.getLogin());
        System.out.println(user.getPassword());


        if (user == null) {
            System.out.println("user null");
            throw new UsernameNotFoundException("User with username: " + login + " not found");
        }


        System.out.println("return user");
        return user;
    }
}
