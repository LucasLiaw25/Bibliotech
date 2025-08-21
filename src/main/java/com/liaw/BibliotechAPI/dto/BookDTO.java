package com.liaw.BibliotechAPI.dto;

import com.liaw.BibliotechAPI.model.Book;
import com.liaw.BibliotechAPI.model.Loan;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BookDTO(
        @NotBlank String title, @NotBlank String author,
        @NotBlank String isbn, @NotNull int yearPublication,
        @NotBlank List<Loan> loans
) {

    public static Book toEntity (BookDTO dto){
        return new Book(
                dto.title,
                dto.author,
                dto.isbn,
                dto.yearPublication,
                dto.loans
        );
    }

    public static BookDTO toDto(Book book){
        return new BookDTO(
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getYearPublication(),
                book.getLoans()
        );
    }

}
