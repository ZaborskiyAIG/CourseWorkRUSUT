package com.CourseWorkRusut.dto;

import java.util.List;

public class LibraryCounterDTO {

    private List<LibraryDTO> content;

    private Long count;

    public LibraryCounterDTO(List<LibraryDTO> content, Long count) {
        this.content = content;
        this.count = count;
    }

    public List<LibraryDTO> getContent() {
        return content;
    }

    public void setContent(List<LibraryDTO> content) {
        this.content = content;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
