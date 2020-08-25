package com.CourseWorkRusut.dto;

import java.util.List;

public class UserCounterDTO {

    private List<UserDTO> content;

    private Long count;

    public UserCounterDTO(List<UserDTO> content , Long count) {
        this.content  = content ;
        this.count = count;
    }

    public List<UserDTO> getContent() {
        return content;
    }

    public void setContent(List<UserDTO> content) {
        this.content = content;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
