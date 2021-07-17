package com.example.library.library.service;

import com.example.library.library.model.Author;
import com.example.library.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getBooks();

    List<Author> getAllAuthors();

    Book createNewBook(Book book);

    Book getBookById(Long pid);

    Book updateBook(Book book);

    void deliteBookById(Long pid);

    List<Book> filter(String str);

}
