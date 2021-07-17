package com.example.library.library.service;

import com.example.library.library.model.Author;
import com.example.library.library.model.Role;
import com.example.library.library.model.Book;
import com.example.library.library.repository.BookRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;


    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return bookRepository.getAllAuthorsForBook();
    }

    @Override
    @Transactional
    public Book createNewBook(Book book) {
        return bookRepository.save(book);

    }

    @Override
    public Book getBookById(Long pid) {
        return bookRepository.getOne(pid);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deliteBookById(Long pid) {
        bookRepository.deleteById(pid);
    }


    @Override
    public List<Book> filter(String str) {
        if (Strings.isNotEmpty(str)) {
            return bookRepository.search(str);
        } else return bookRepository.findAll();
    }

}
