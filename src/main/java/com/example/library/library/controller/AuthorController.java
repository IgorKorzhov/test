package com.example.library.library.controller;

import com.example.library.library.model.Author;
import com.example.library.library.model.Book;
import com.example.library.library.model.User;
import com.example.library.library.service.AuthorService;
import com.example.library.library.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/references")
public class AuthorController {
    private static final String AUTHOR_TABLE = "references/authors/authorTable :: author_list";
    private static final String EDIT_MODAL = "references/authors/modal/editAuthor";
    private static final String ADD_MODAL = "references/authors/modal/addAuthor";

    AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


   /* @GetMapping("/authors")
    public String getAll(Model model) {
        /*Author author = new Author(1L, "Ведьмак", "Ведьмович",
                "Ведьмаков", Calendar.getInstance().getTime(), "M");

        model.addAttribute("authors", Collections.singletonList(author));
        List<Author> author= authorService.getAuthors();
        model.addAttribute("author", author);

        return "references/authors/author";
    }*/

    @GetMapping("authors/addAuthor")
    public String addAuthor(Model model) {
        try {
            model.addAttribute("author", new Author());
            return ADD_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ADD_MODAL;
        }
    }

    @PostMapping("authors/saveAuthor")
    public String saveAuthor(Author author, Model model) {
        try {
            authorService.createNewAuthor(author);
            model.addAttribute("authors", authorService.getAuthors());
            model.addAttribute("message", "Автор успешно добавлен");
            model.addAttribute("alertClass", "alert-success");
            return AUTHOR_TABLE;
        } catch (Exception e) {
            model.addAttribute("authors", authorService.getAuthors());
            model.addAttribute("message", "Ошибка добавления автора");
            model.addAttribute("alertClass", "alert-danger");
            return AUTHOR_TABLE;
        }
    }

    @GetMapping("authors/edit")
    public String editAuthor(Long pid, Model model) {
        try {
            Author author = authorService.getAuthorById(pid);
            model.addAttribute("predefinedBooks", authorService.getAllBooks());
            model.addAttribute("author", author);
            return EDIT_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return EDIT_MODAL;
        }

    }

    @PostMapping("authors/updateAuthor")
    public String updateAuthor(Author author, Model model) {
        try {
            authorService.updateAuthor(author);
            model.addAttribute("authors", authorService.getAuthors());
            model.addAttribute("message", "Автор успешно изменён");
            model.addAttribute("alertClass", "alert-success");
            return AUTHOR_TABLE;
        } catch (Exception e) {
            model.addAttribute("authors", authorService.getAuthors());
            model.addAttribute("message", "Ошибка редактирования автора");
            model.addAttribute("alertClass", "alert-danger");
            return AUTHOR_TABLE;
        }
    }

    @RequestMapping(value = "authors/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteAuthor(Long pid, Model model) {
        try {
            authorService.deliteAuthorById(pid);
            model.addAttribute("authors", authorService.getAuthors());
            model.addAttribute("message", "Автор успешно удалён");
            model.addAttribute("alertClass", "alert-success");
            return AUTHOR_TABLE;
        } catch (Exception e) {
            model.addAttribute("authors", authorService.getAuthors());
            model.addAttribute("message", "Ошибка удаления автора");
            model.addAttribute("alertClass", "alert-danger");
            return AUTHOR_TABLE;
        }
    }

    @GetMapping("authors/filterAuthor")
    public String filter(String str, Model model) {
        List<Author> authors = authorService.filter(str);
        try {
            model.addAttribute("authors", authors);
            return AUTHOR_TABLE;
        } catch (Exception e) {
            model.addAttribute("authors", authorService.getAuthors());
            model.addAttribute("message", "При работе с авторами произошла ошибка");
            model.addAttribute("alertClass", "alert-danger");
            return AUTHOR_TABLE;
        }
    }
    @RequestMapping(value = {"/authors"}, method = RequestMethod.GET)
    public String authors(Model model) {
        model.addAttribute("authors", authorService.getAuthors());
        return "references/authors/author";
    }
}



