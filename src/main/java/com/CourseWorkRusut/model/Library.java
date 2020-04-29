package com.CourseWorkRusut.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "library")
public class Library {

    @Id
    @Column(name = "library_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libraryId;

    @Column(name = "name")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data")
    private LocalDate data = LocalDate.now();  //проверить как создается LocalDate

    @Column(name = "book")
    private byte[] book;

    @ManyToOne
    @JoinColumn(name = "Specialty_id")
    private Specialty specialty;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "author_library",
            joinColumns = { @JoinColumn(name = "library_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id")})
    private List<Author> authors;

    public long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(long libraryId) {
        this.libraryId = libraryId;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBook() {
        return book;
    }

    public void setBook(byte[] book) {
        this.book = book;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }


}
