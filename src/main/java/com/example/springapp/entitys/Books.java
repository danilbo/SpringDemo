package com.example.springapp.entitys;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "author")
    private String author;
    @Column(name = "title")
    private String title;

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public Books() {
    }

    public Books(String author, String title) {
        this.author = author;
        this.title = title;
    }

    @Override
    public String toString(){
        return this.id + " " + this.author + " " + this.title;
    }
}