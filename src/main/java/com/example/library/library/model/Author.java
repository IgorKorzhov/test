package com.example.library.library.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();


    public Author(long pid, String firstName, String middleName, String lastName) {
        this.pid = pid;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Author() {
    }
    @Transient
    public String getBooksStr(){
        return books.stream().map(Book::getName).collect(Collectors.joining(","));
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getFIO() {
        if (getMiddleName() == null) {
            return getLastName() + ". " + getFirstName().substring(1);
        }

        if (getFirstName() == null) {
            return getLastName() + " " + getMiddleName().charAt(0)+ ".";
        }
        if (getMiddleName() == null && getFirstName() == null) {
            return getLastName() + " " + getMiddleName().charAt(0)+ ".";
        } else return getLastName() + " " + getFirstName().charAt(0) + ". " + getMiddleName().charAt(0)+ ".";
    }
}