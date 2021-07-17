package com.example.library.library.repository;

import com.example.library.library.model.Book;

import java.util.List;

public interface BookRepositoryCustom {

    List<Book> search(String str);
}
