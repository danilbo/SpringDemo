package com.example.springapp.controllers;

import com.example.springapp.entitys.BookForm;
import com.example.springapp.entitys.Books;
import com.example.springapp.repository.Repository;
import com.example.springapp.thymeleaf.Person;
import com.example.springapp.thymeleaf.PersonForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/base")
public class BookController {

    @Autowired
    public Repository repository;

    @Value("${error.message}")
    private String errorMessage;

    private static List<Books> books = new ArrayList<Books>();


    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = ("whoever you are...")) String name) {
        return String.format("Hello, %s!", name);
    }

//    @GetMapping("/getdb")
//    public Iterable<Books> getDB() {
//        return repository.findAll();
//    }
//

    @RequestMapping(value = { "/booksList" }, method = RequestMethod.GET)
    public String personList(Model model) {

        List<Books> booksArray = new ArrayList<>();
        repository.findAll().forEach(booksArray::add);
        model.addAttribute("books", booksArray);

        return "booksList";
    }


//    @PostMapping("/add")
//    public String addNewBook(){
//
//        Books b = new Books();
//
//        //b.setAuthor(author)
//        //b.setTitle(title)
//
//        repository.save(b);
//        return "Saved";
//    }


    @RequestMapping(value = { "/addBook" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        BookForm bookForm = new BookForm();
        model.addAttribute("bookForm", bookForm);

        return "addBook";
    }


    @RequestMapping(value = { "/addBook" }, method = RequestMethod.POST)
    public String savePerson(Model model, //
                             @ModelAttribute("bookForm") BookForm bookForm) {

        String author = bookForm.getAuthor();
        String title = bookForm.getTitle();

        if (author != null && author.length() > 0 //
                && title != null && title.length() > 0) {
            Books newBook = new Books(author, title);
            repository.save(newBook);

            return "redirect:/base/booksList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addBook";
    }


}


