package com.CourseWorkRusut.model;

import javax.persistence.*;
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

    @Column(name = "author")
    private String author;

    //@Column(name = "data")
    //private Date data

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getBook() {
        return book;
    }

    public void setBook(byte[] book) {
        this.book = book;
    }

    /*public void setData(??? data) {
        this.data = data;
    }

    public ??? getData() {
        return data;
    }*/


}
