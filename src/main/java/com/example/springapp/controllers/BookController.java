package com.example.springapp.controllers;

import com.example.springapp.BookService;
import com.example.springapp.entitys.BookForm;
import com.example.springapp.entitys.Books;
import com.example.springapp.repository.Repository;
import com.example.springapp.thymeleaf.Person;
import com.example.springapp.thymeleaf.PersonForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/base")
public class BookController {


    @Autowired
    private BookService bookService;

    @Value("${error.message}")
    private String errorMessage;

    private final static String author = "author";
    private final static String title = "title";


    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = ("whoever you are...")) String name) {
        return String.format("Hello, %s!", name);
    }


    @RequestMapping(value = {"/booksList"}, method = RequestMethod.GET)
    public String personList(@RequestParam(value = "task", defaultValue = ("id")) String task, Model model) {


        List<Books> booksArray = new ArrayList<>();


        if (task.equals(author)) {
            booksArray = bookService.sortedByAuthor();
        }
        if (task.equals(title)) {
            booksArray = bookService.sortedByTitle();
        }
        if (task.equals("id")) {
            booksArray = bookService.sortedByID();
        }

        model.addAttribute("books", booksArray);
        return "booksList";
    }

    @RequestMapping(value = {"/booksListPg"}, method = RequestMethod.GET)
    public String pageablePersonList(
            @RequestParam(value = "task", defaultValue = ("id")) String task,
            Model model,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable) {



        Page<Books> booksPage = null;



        if (task.equals("author")) {
            booksPage = bookService.sortedByAuthorPage(pageable);
        }
        if (task.equals("title")) {
            booksPage = bookService.sortedByTitlePage(pageable);
        }
        if (task.equals("id")) {
            booksPage = bookService.sortedByIDPage(pageable);
        }

        int totalPages = booksPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList()); //creating page list with stream
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("books", booksPage); //bookPage
        return "booksListPg";
    }



    @RequestMapping(value = {"/addBook"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        BookForm bookForm = new BookForm();
        model.addAttribute("bookForm", bookForm);

        return "addBook";
    }


    @RequestMapping(value = {"/addBook"}, method = RequestMethod.POST)
    public String savePerson(Model model, //
                             @ModelAttribute("bookForm") BookForm bookForm) {

        String author = bookForm.getAuthor();
        String title = bookForm.getTitle();

        if (author != null && author.length() > 0 &&
            title != null && title.length() > 0) {
            return bookService.addBook(author, title);
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addBook";
    }


}


