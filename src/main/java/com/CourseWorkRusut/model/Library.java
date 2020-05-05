package com.CourseWorkRusut.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "library")
public class Library {

    @Id
    @Column(name = "library_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libraryId;

    @Column(name = "name")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
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
    private Set<Author> authors;

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

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
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

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(libraryId, library.libraryId) &&
                Objects.equals(name, library.name) &&
                Objects.equals(data, library.data) &&
                Arrays.equals(book, library.book) &&
                Objects.equals(specialty, library.specialty) &&
                Objects.equals(authors, library.authors);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(libraryId, name, data, specialty, authors);
        result = 31 * result + Arrays.hashCode(book);
        return result;
    }
}
