package com.CourseWorkRusut.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class LibraryDTO  {

    private Long libraryId;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate data;

  //  private byte[] book;

    private List<String> authors;

    public LibraryDTO() {

    }

    public LibraryDTO(Long libraryId, String name, LocalDate data, List<String> authors) {
        this.libraryId = libraryId;
        this.name = name;
        this.data = data;
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

   // public byte[] getBook() {
  //      return book;
 //   }

   // public void setBook(byte[] book) {
  //      this.book = book;
   // }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryDTO that = (LibraryDTO) o;
        return Objects.equals(libraryId, that.libraryId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(data, that.data) &&
                Objects.equals(authors, that.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryId, name, data, authors);
    }
}
