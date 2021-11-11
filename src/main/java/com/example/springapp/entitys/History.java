package com.example.springapp.entitys;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Books books;

    @ManyToOne
    private Reader reader;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public Reader getReader() {
        return reader;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", books=" + books +
                ", reader=" + reader +
                '}';
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
