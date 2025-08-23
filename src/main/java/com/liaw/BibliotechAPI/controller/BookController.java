package com.liaw.BibliotechAPI.controller;

import com.liaw.BibliotechAPI.dto.BookDTO;
import com.liaw.BibliotechAPI.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody @Valid BookDTO dto){
        return service.createBook(dto);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> searchBook(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String isbn
    ){
        return service.searchBook(id, title, author, isbn);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO dto){
        return service.updateBook(id, dto);
    }

}
