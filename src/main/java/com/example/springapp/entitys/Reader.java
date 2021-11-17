package com.example.springapp.entitys;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="reader")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String second_name;
    @OneToOne
    private Books currentBook;
    @OneToMany(targetEntity = History.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "reader_id")
    private List<History> histories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public void setBooks(Books books) {
        this.currentBook = books;
    }

    public Books getBooks() {
        return currentBook;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    public void setCurrentBook(Books currentBook) {
        this.currentBook = currentBook;
    }

    public Books getCurrentBook() {
        return currentBook;
    }

    @Override
    public String toString() {
        //return "Reader{id + "+getId()+" name " + getFirst_name()+ " surname " + getSecond_name() + "}";
        return "{"+ getFirst_name()+ " " + getSecond_name() +"}";
    }
}

