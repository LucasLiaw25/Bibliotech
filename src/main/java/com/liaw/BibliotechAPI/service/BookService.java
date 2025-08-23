package com.liaw.BibliotechAPI.service;

import com.liaw.BibliotechAPI.dto.BookDTO;
import com.liaw.BibliotechAPI.model.Book;
import com.liaw.BibliotechAPI.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public ResponseEntity<BookDTO> createBook(BookDTO dto){
        Book book = dto.toEntity();
        repository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    public ResponseEntity<List<BookDTO>> searchBook(
            Long id, String title, String author, String isbn
    ){
        Book book = new Book();
        if (id != null) {
            book.setId(id);
        }
        if (title != null) {
            book.setTitle(title);
        }
        if (author != null) {
            book.setAuthor(author);
        }
        if (isbn != null) {
            book.setIsbn(isbn);
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Book> example = Example.of(book, matcher);
        List<Book> books_found = repository.findAll(example);
        List<BookDTO> dto = books_found.stream()
                .map(BookDTO::toDto).toList();

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<BookDTO> updateBook(Long id, BookDTO bookDTO){
        Optional<Book> book_search = repository.findById(id);

        if (book_search.isPresent()){
            Book book = book_search.get();
            book = bookDTO.toEntity();
            book.setId(id);
            BookDTO dto = BookDTO.toDto(book);
            repository.save(book);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

}
