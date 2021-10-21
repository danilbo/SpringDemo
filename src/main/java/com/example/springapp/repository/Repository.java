package com.example.springapp.repository;

import com.example.springapp.entitys.Books;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<Books, Long> {

}
