package com.CourseWorkRusut.DTO;

import java.util.List;

public class UserCounterDTO { //попробовать на основе этой dto сделать запрос к базе

    private List<UserDTO> users;

    private Long count;

    public UserCounterDTO(List<UserDTO> users, Long count) {
        this.users = users;
        this.count = count;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
