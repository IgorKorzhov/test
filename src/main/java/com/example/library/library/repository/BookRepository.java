package com.example.library.library.repository;

import com.example.library.library.model.Author;
import com.example.library.library.model.Role;
import com.example.library.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

    Optional<Book> findByName(String name);

    @Query("select a from Author a")
    List<Author> getAllAuthorsForBook();

}
