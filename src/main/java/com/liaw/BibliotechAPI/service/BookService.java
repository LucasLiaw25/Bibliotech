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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public ResponseEntity<BookDTO> createBook(BookDTO dto){
        Book book = dto.toEntity();
        repository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    public ResponseEntity<List<BookDTO>> listBook(){
        List<Book> book = repository.findAll();
        List<BookDTO> bookDto = book.stream()
                .map(BookDTO::toDto)
                .toList();
        return ResponseEntity.ok(bookDto);
    }

    public ResponseEntity<List<BookDTO>> searchBook(
            Long id, String title, String author, String isbn
    ){
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Book> example = Example.of(book, matcher);

        List<Book> book_found = repository.findAll(example);
        List<BookDTO> dto = book_found.stream()
                .map(BookDTO::toDto).toList();

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<BookDTO> updateBook(Long id, BookDTO dto){
        Optional<Book> book = repository.findById(id);
        if (book.isPresent()){
            Book book_found = dto.toEntity();
            book_found.setId(id);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

}
