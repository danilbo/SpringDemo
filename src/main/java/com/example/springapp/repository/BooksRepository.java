package com.example.springapp.repository;

import com.example.springapp.entitys.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BooksRepository extends JpaRepository<Books,Long>, JpaSpecificationExecutor<Books> { }
