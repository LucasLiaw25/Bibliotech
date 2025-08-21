package com.liaw.BibliotechAPI.repository;

import com.liaw.BibliotechAPI.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIdAndTitleAndAuthorAndIsbn(
            Long id, String title, String author, String isbn
    );
}
