package com.example.library.library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20)")
    private Genre genre;

    @JoinColumn(name="author_pid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date yearOfIssue;

    @Column(nullable = false)
    private Long totalOfSheets;

    @Column(nullable = false, columnDefinition = "varchar(20) default '0'")
    private Long totalOfBooks;

    @Column(nullable = false, columnDefinition = "varchar(20) default '0'")
    private Long totalOfBooksInLibrary;


    public Book(Long pid, String name, Genre genre, Author author, Date yearOfIssue, Long totalOfSheets, Long totalOfBooks, Long totalOfBooksInLibrary) {
        this.pid = pid;
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.yearOfIssue = yearOfIssue;
        this.totalOfSheets = totalOfSheets;
        this.totalOfBooks = totalOfBooks;
        this.totalOfBooksInLibrary = totalOfBooksInLibrary;
    }

    public Book() {

    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(Date yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public Long getTotalOfSheets() {
        return totalOfSheets;
    }

    public void setTotalOfSheets(Long totalOfSheets) {
        this.totalOfSheets = totalOfSheets;
    }

    public Long getTotalOfBooks() {
        return totalOfBooks;
    }

    public void setTotalOfBooks(Long totalOfBooks) {
        this.totalOfBooks = totalOfBooks;
    }

    public Long getTotalOfBooksInLibrary() {
        return totalOfBooksInLibrary;
    }

    public void setTotalOfBooksInLibrary(Long totalOfBooksInLibrary) {
        this.totalOfBooksInLibrary = totalOfBooksInLibrary;
    }



}
