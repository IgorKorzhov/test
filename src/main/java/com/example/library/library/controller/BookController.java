package com.example.library.library.controller;

import com.example.library.library.model.Author;
import com.example.library.library.model.Book;
import com.example.library.library.model.Genre;
import com.example.library.library.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
@RequestMapping("/references")
public class BookController {

    private static final String BOOK_TABLE = "references/books/bookTable :: book_list";;
    private static final String EDIT_MODAL = "references/books/modal/editBook";
    private static final String ADD_MODAL = "references/books/modal/addBook";

    BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

   /* @GetMapping("/books")
    public String getAll(Model model) {
       /*Book book = new Book(1L, "Ведьмак", new Genre(1),
                new Author(), new Date(), 5L,2L);
        book.setTotalOfBooks(50L);
        model.addAttribute("books", Collections.singletonList(book));
        return "references/books/book";
    }*/

    @GetMapping("books/addBook")
    public String addBook(Model model) {
        try {
            model.addAttribute("predefinedAuthor", bookService.getAllAuthors());
            model.addAttribute("book", new Book());
            return ADD_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ADD_MODAL;
        }
    }

    @PostMapping("books/saveBook")
    public String saveBook(Book book, Model model) {
        try {
            bookService.createNewBook(book);
            model.addAttribute("books", bookService.getBooks());
            model.addAttribute("message", "Книга успешно добавлена");
            model.addAttribute("alertClass", "alert-success");
            return BOOK_TABLE;
        } catch (Exception e) {
            model.addAttribute("books", bookService.getBooks());
            model.addAttribute("message", "Ошибка добавления книги");
            model.addAttribute("alertClass", "alert-danger");
            return BOOK_TABLE;
        }
    }

    @GetMapping("books/edit")
    public String editBook(Long pid, Model model) {
        try {
            Book book = bookService.getBookById(pid);
            model.addAttribute("predefinedAuthor", bookService.getAllAuthors());
            model.addAttribute("book", book);
            return EDIT_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return EDIT_MODAL;
        }

    }

    @PostMapping("books/updateBook")
    public String updateBook(Book book, Model model) {
        try {
            bookService.updateBook(book);
            model.addAttribute("books", bookService.getBooks());
            model.addAttribute("message", "Книга успешно изменена");
            model.addAttribute("alertClass", "alert-success");
            return BOOK_TABLE;
        } catch (Exception e) {
            model.addAttribute("books", bookService.getBooks());
            model.addAttribute("message", "Ошибка редактирования книги");
            model.addAttribute("alertClass", "alert-danger");
            return BOOK_TABLE;
        }
    }

    @RequestMapping(value = "books/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteBook(Long pid, Model model) {
        try {
            bookService.deliteBookById(pid);
            model.addAttribute("books", bookService.getBooks());
            model.addAttribute("message", "Книга успешно удалёна");
            model.addAttribute("alertClass", "alert-success");
            return BOOK_TABLE;
        } catch (Exception e) {
            model.addAttribute("books", bookService.getBooks());
            model.addAttribute("message", "Ошибка удаления книги");
            model.addAttribute("alertClass", "alert-danger");
            return BOOK_TABLE;
        }
    }

    @GetMapping("books/filterBook")
    public String filter(String str, Model model) {
        List<Book> books = bookService.filter(str);
        try {
            model.addAttribute("books", books);
            return BOOK_TABLE;
        } catch (Exception e) {
            model.addAttribute("books", bookService.getBooks());
            model.addAttribute("message", "При работе с книгами произошла ошибка");
            model.addAttribute("alertClass", "alert-danger");
            return BOOK_TABLE;
        }
    }

    @RequestMapping(value = {"books"}, method = RequestMethod.GET)
    public String books(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "references/books/book";
    }
}
