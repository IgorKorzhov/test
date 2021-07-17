package com.example.library.library.repository;

import com.example.library.library.model.Author;
import com.example.library.library.model.Book;

import java.util.List;

public interface AuthorRepositoryCustom {

    List<Author> search(String str);
}
