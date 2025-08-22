package com.liaw.BibliotechAPI.dto;

import com.liaw.BibliotechAPI.model.Book;
import com.liaw.BibliotechAPI.model.Loan;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.ISBN;

import java.util.List;

public record BookDTO(
        @NotBlank String title, @NotBlank String author,
        @NotBlank String isbn, @NotNull int yearPublication,
        List<Loan> loans
) {

    public Book toEntity() {
        return new Book(
                this.title(),
                this.author(),
                this.isbn(),
                this.yearPublication(),
                this.loans()
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
