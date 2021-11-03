package com.example.springapp.services;

import com.example.springapp.entitys.Books;
import com.example.springapp.repository.BooksSpecificationsBuilder;
import com.example.springapp.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    private Repository repository;

    private List<Books> books;



    public Page<Books> getPageBooks(Map<String,String> conditions, Pageable pageable, String sort, int size) {
        BooksSpecificationsBuilder builder = new BooksSpecificationsBuilder();
        conditions.entrySet().stream().forEach(e -> builder.with(e.getKey() , e.getValue()));

        int pageSize = size;
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Books> list;

        Specification<Books> spec = builder.build();
        list = repository.findAll(spec, Sort.by(sort));

        if (list.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, list.size());
            list = list.subList(startItem, toIndex);
        }
        Page<Books> bookPage
                = new PageImpl<Books>(list, PageRequest.of(currentPage, pageSize), list.size());
        return bookPage;
    }

    public List<Books> getParamsBooks(Map<String,String> conditions) {
        BooksSpecificationsBuilder builder = new BooksSpecificationsBuilder();

        conditions.entrySet().stream().forEach(e -> builder.with(e.getKey() , e.getValue()));

        Specification<Books> spec = builder.build();
        return repository.findAll(spec);
    }


    public Page<Books> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Books> list;

        if (books.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, books.size());
            list = books.subList(startItem, toIndex);
        }

        Page<Books> bookPage
                = new PageImpl<Books>(list, PageRequest.of(currentPage, pageSize), books.size());

        return bookPage;
    }


    public String addBook(String author, String title){
            Books newBook = new Books(author, title);
            repository.save(newBook);
            return "redirect:/base/booksList";
    }

    public List<Books> sortedByID() {
        return repository.findByOrderByIdAsc();
    }
    public List<Books> sortedByAuthor() {
        return repository.findByOrderByAuthorAsc();
    }
    public List<Books> sortedByTitle() {
        return repository.findByOrderByTitleAsc();
    }
    public Page<Books> sortedByIDPage(Pageable pageable) {
        return repository.findByOrderByIdAsc(pageable);
    }
    public Page<Books> sortedByAuthorPage(Pageable pageable) {
        return repository.findByOrderByAuthorAsc(pageable);
    }
    public Page<Books> sortedByTitlePage(Pageable pageable) {
        return repository.findByOrderByTitleAsc(pageable);
    }





}