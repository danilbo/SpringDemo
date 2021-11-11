package com.example.springapp.entitys;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "author")
    private String author;
    @Column(name = "title")
    private String title;
    @OneToOne
    @JoinColumn(name = "id")
    private Reader reader;
    @OneToMany(targetEntity = History.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private List<History> histories;

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

    public Long getId() {
        return id;
    }

    public Books() {
    }

    public Books(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    public List<History> getHistories() {
        return histories;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader readers) {
        this.reader = readers;
    }

    @Override
    public String toString(){
        return this.id + " " + this.author + " " + this.title;
    }
}