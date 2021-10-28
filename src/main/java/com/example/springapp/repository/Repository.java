package com.example.springapp.repository;

import com.example.springapp.entitys.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface Repository extends CrudRepository<Books, Long> {

    ArrayList<Books> findByOrderByIdAsc();
    ArrayList<Books> findByOrderByAuthorAsc();
    ArrayList<Books> findByOrderByTitleAsc();

    Iterable<Books> findAll(Sort sort);

    Page<Books>  findByOrderByIdAsc(Pageable pageable);
    Page<Books> findByOrderByAuthorAsc(Pageable pageable);
    Page<Books> findByOrderByTitleAsc(Pageable pageable);

}
