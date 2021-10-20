package com.example.springapp.entitys;


import javax.persistence.*;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private  String title;

    public Book(){}

}
