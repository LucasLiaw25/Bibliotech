package com.liaw.BibliotechAPI.dto;

import com.liaw.BibliotechAPI.model.Book;
import com.liaw.BibliotechAPI.model.Loan;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BookDTO(
        Long id, @NotBlank String title, @NotBlank String author,
        @NotBlank String isbn, @NotNull int yearPublication, Boolean active,
        List<Loan> loans
) {

    public Book toEntity(){
        return new Book(
                this.id,
                this.title,
                this.author,
                this.isbn,
                this.yearPublication,
                this.active,
                this.loans
        );
    }

    public static BookDTO toDto(Book book){
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getYearPublication(),
                book.getActive(),
                book.getLoans()
        );
    }

}
