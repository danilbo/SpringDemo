package com.example.springapp.controllers;

import com.example.springapp.entitys.Books;
import com.example.springapp.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/base")
public class BookController {

    @Autowired
    public Repository repository;


    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = ("whoever you are...")) String name) {
        return String.format("Hello, %s!", name);
    }

    @GetMapping("/getdb")
    public @ResponseBody Iterable<Books> getDB() {

        return repository.findAll();

    }


}


