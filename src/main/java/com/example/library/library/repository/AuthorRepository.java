package com.example.library.library.repository;

import com.example.library.library.model.Author;
import com.example.library.library.model.Book;
import com.example.library.library.model.Role;
import com.example.library.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long>, AuthorRepositoryCustom {

    @Query("select b from Book b")
    List<Book> getAllBooksForAuthor();

}
