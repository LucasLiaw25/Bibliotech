package com.liaw.BibliotechAPI.repository;

import com.liaw.BibliotechAPI.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
