package com.CourseWorkRusut.DTO;

import java.util.List;

public class LibraryCounterDTO {

    private List<LibraryDTO> libraryDTOList;

    private Long count;

    public LibraryCounterDTO(List<LibraryDTO> libraryDTOList, Long count) {
        this.libraryDTOList = libraryDTOList;
        this.count = count;
    }

    public List<LibraryDTO> getLibraryDTOList() {
        return libraryDTOList;
    }

    public void setLibraryDTOList(List<LibraryDTO> libraryDTOList) {
        this.libraryDTOList = libraryDTOList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
