package com.example.springapp.repository;

import com.example.springapp.entitys.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface Repository extends JpaRepository<Books, Long> {

    ArrayList<Books> findByOrderByAuthorAsc();
    ArrayList<Books> findByOrderByTitleAsc();

}
