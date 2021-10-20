package com.example.springapp.repos;

import com.example.springapp.entitys.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRep extends CrudRepository<Book,Long> {
}
