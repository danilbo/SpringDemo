package com.example.springapp.services;

import com.example.springapp.entitys.Books;
import com.example.springapp.entitys.History;
import com.example.springapp.entitys.Reader;
import com.example.springapp.repository.BooksSpecificationsBuilder;
import com.example.springapp.repository.HistoryRepository;
import com.example.springapp.repository.ReaderRepository;
import com.example.springapp.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    private Repository repository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private HistoryRepository historyRepository;

    //need to add new view smthng like "Reader List"
    @Transactional
    public String addReader(Reader reader){
        Reader newReader = new Reader();
        newReader.setFirst_name(reader.getFirst_name());
        newReader.setSecond_name(reader.getSecond_name());

        Reader savedReader = readerRepository.save(newReader);
        if (readerRepository.findById(savedReader.getId()).isPresent()){
            ResponseEntity.accepted().body("Successfully Created");
        }else{
            ResponseEntity.unprocessableEntity().body("Failed to Create");
        }
        return "";
    }

    @Transactional
    public String addBook(Books book){
        Books newBook = new Books();
        newBook.setAuthor(book.getAuthor());
        newBook.setTitle(book.getTitle());

        Books savedBook = repository.save(newBook);
        if (repository.findById(savedBook.getId()).isPresent()){
            ResponseEntity.accepted().body("Successfully Created");
        }else{
            ResponseEntity.unprocessableEntity().body("Failed to Create");
        }
        return "redirect:/base/booksList";
    }

    //public String deleteBook multiTable delete???
    //public String deleteReader multiTable delete???

    //pageable with criteria
    public Page<Books> getPageBooks(Map<String,String> conditions, Pageable pageable, String sort, int size) {
        int pageSize = size;
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Books> list;

        BooksSpecificationsBuilder builder = new BooksSpecificationsBuilder();
        conditions.entrySet().stream().forEach(e -> builder.with(e.getKey() , e.getValue()));

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

    public String addBook(String author, String title){
            Books newBook = new Books(author, title);
            repository.save(newBook);
            return "redirect:/base/booksList";
    }
    public List<History> getSuperEntities(){return historyRepository.findAll();}


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