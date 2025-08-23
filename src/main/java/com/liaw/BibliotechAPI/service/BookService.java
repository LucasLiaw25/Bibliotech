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
import org.springframework.transaction.annotation.Transactional;
import com.liaw.BibliotechAPI.validation.BookValidation;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookValidation validation;

    public ResponseEntity<BookDTO> createBook(BookDTO dto){
        Book book = dto.toEntity();
        repository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
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
        validation.validateBook(bookDTO);
        Book book = bookDTO.toEntity();
        book.setId(id);
        repository.save(book);
        return ResponseEntity.ok(bookDTO);
    }

    @Transactional
    public ResponseEntity<String> deleteBook(Long id){
        Optional<Book> book = repository.findById(id);
        if (book.isPresent()){
            Book book_found = book.get();
            book_found.setActive(false);
            return ResponseEntity.ok("Livro desativado");
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<BookDTO> activeBook(Long id){
        Optional<Book> book = repository.findById(id);
        Book book_found = book.get();
        if (book.isPresent()&&book_found.getActive()==false){
            book_found.setActive(true);
            repository.save(book_found);
            BookDTO dto = BookDTO.toDto(book_found);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

}
