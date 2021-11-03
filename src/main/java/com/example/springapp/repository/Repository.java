package com.example.springapp.repository;

import com.example.springapp.entitys.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface Repository extends CrudRepository<Books, Long>, JpaRepository<Books, Long>, JpaSpecificationExecutor<Books> {

    ArrayList<Books> findByOrderByIdAsc();
    ArrayList<Books> findByOrderByAuthorAsc();
    ArrayList<Books> findByOrderByTitleAsc();



    Page<Books>  findByOrderByIdAsc(Pageable pageable);
    Page<Books> findByOrderByAuthorAsc(Pageable pageable);
    Page<Books> findByOrderByTitleAsc(Pageable pageable);

}
