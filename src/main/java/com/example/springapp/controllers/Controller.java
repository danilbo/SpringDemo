package com.example.springapp.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base")
public class Controller {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = ("whoever you are...")) String name) {
        return String.format("Hello, %s!", name);
    }
}
//asdaddadad