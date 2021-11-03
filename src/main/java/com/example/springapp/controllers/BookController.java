package com.example.springapp.controllers;

import com.example.springapp.repository.BooksSpecificationsBuilder;
import com.example.springapp.repository.Repository;
import com.example.springapp.services.BookService;
import com.example.springapp.dto.BookForm;
import com.example.springapp.entitys.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/base")
public class BookController {


    @Autowired
    private BookService bookService;
    @Autowired
    private Repository repository;

    @Value("${error.message}")
    private String errorMessage;


    @RequestMapping(method = RequestMethod.GET, value = "/filter")
    public String search(@RequestParam(value = "author",required = false) String author,Model model,
                         @RequestParam(value = "title",required = false) String title,
                         @RequestParam(value = "sort",defaultValue = "id") String sort,
                         @RequestParam(value = "pageSize",defaultValue = "6") String pageSize,
                         @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable) {
        int size = Integer.parseInt(pageSize);
        Map<String,String> conditions = new HashMap<String,String>();
        if (author!=null && !author.equals("")) conditions.put("author", author);
        if (title!=null && !title.equals(""))  conditions.put("title" , title);
        //List<Books> booksArray = bookService.getParamsBooks(conditions);

        Page<Books> page = bookService.getPageBooks(conditions, pageable, sort, size);
        model.addAttribute("books", page);
        return "booksList";
    }


    @RequestMapping(value = {"/booksList"}, method = RequestMethod.GET)
    public String booksList(@RequestParam(value = "task", defaultValue = ("id")) String task, Model model) {

        List<Books> booksArray = new ArrayList<>();

        if (task.equals("author")) {
            booksArray = bookService.sortedByAuthor();
        }
        if (task.equals("title")) {
            booksArray = bookService.sortedByTitle();
        }
        if (task.equals("id")) {
            booksArray = bookService.sortedByID();
        }

        model.addAttribute("books", booksArray);
        return "booksList";
    }

    @RequestMapping(value = {"/booksListPg"}, method = RequestMethod.GET)
    public String pageableBooksList(
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
    public String showAddBookPage(Model model) {

        BookForm bookForm = new BookForm();
        model.addAttribute("bookForm", bookForm);

        return "addBook";
    }


    @RequestMapping(value = {"/addBook"}, method = RequestMethod.POST)
    public String saveBook(Model model, @ModelAttribute("bookForm") BookForm bookForm) {

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


