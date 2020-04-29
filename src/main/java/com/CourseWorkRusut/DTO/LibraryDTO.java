package com.CourseWorkRusut.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class LibraryDTO {
    private Long libraryId;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    private byte[] book;

    private List<AuthorDTO> authors;

    public LibraryDTO() {

    }

    public LibraryDTO(Long libraryId, String name, LocalDate data, byte[] book, List<AuthorDTO> authors) {
        this.libraryId = libraryId;
        this.name = name;
        this.data = data;
        this.book = book;
        this.authors = authors;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public byte[] getBook() {
        return book;
    }

    public void setBook(byte[] book) {
        this.book = book;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }
}
