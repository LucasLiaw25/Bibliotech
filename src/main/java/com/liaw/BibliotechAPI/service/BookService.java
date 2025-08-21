package com.liaw.BibliotechAPI.service;

import com.liaw.BibliotechAPI.dto.BookDTO;
import com.liaw.BibliotechAPI.model.Book;
import com.liaw.BibliotechAPI.repository.BookRepository;
import lombok.RequiredArgsConstructor;
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
        Book book = BookDTO.toEntity(dto);
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

    public ResponseEntity<BookDTO> searchBook(
            Long id, String title,
            String author, String isbn
    ){
        Optional<Book> book = repository.findByIdAndTitleAndAuthorAndIsbn(
                id, title, author, isbn
        );
        if (book.isPresent()){
            Book book_found = book.get();
            BookDTO dto = BookDTO.toDto(book_found);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<BookDTO> updateBook(Long id, BookDTO dto){
        Optional<Book> book = repository.findById(id);
        if (book.isPresent()){
            Book book_found = BookDTO.toEntity(dto);
            book_found.setId(id);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

}
