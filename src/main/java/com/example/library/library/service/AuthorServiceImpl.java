package com.example.library.library.service;

import com.example.library.library.model.Author;
import com.example.library.library.model.Book;
import com.example.library.library.model.Role;
import com.example.library.library.model.User;
import com.example.library.library.repository.AuthorRepository;
import com.example.library.library.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;


    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;


    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return authorRepository.getAllBooksForAuthor();
    }

    @Override
    @Transactional
    public Author createNewAuthor(Author author) {
        return authorRepository.save(author);

    }

    @Override
    public Author getAuthorById(Long pid) {
        return authorRepository.getOne(pid);
    }

    @Override
    @Transactional
    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deliteAuthorById(Long pid) {
        authorRepository.deleteById(pid);
    }


    @Override
    public List<Author> filter(String str) {
        if (Strings.isNotEmpty(str)) {
            return authorRepository.search(str);
        } else return authorRepository.findAll();
    }

}
