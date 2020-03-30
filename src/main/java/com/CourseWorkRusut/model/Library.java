package com.CourseWorkRusut.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "library")
public class Library {

    @Id
    @Column(name = "library_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long libraryId;

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

    public long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(long libraryId) {
        this.libraryId = libraryId;
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
