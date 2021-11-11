package com.example.springapp.repository;

import com.example.springapp.entitys.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReaderRepository extends JpaRepository<Reader,Long> {


}
