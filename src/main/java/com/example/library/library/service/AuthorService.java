package com.example.library.library.service;

import com.example.library.library.model.Author;
import com.example.library.library.model.Book;
import com.example.library.library.model.Role;
import com.example.library.library.model.User;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAuthors();

    List<Book> getAllBooks();

    Author createNewAuthor(Author author);

    Author getAuthorById(Long pid);

    Author updateAuthor(Author author);

    void deliteAuthorById(Long pid);

    List<Author> filter(String str);

}
