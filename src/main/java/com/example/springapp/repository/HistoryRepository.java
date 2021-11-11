package com.example.springapp.repository;

import com.example.springapp.entitys.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History,Long> {
}
