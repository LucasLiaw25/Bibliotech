package com.liaw.BibliotechAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;

    @Column(unique = true)
    private String isbn;
    private int yearPublication;
    private boolean active = true;

    @OneToMany(mappedBy = "book")
    private List<Loan> loans;

    public Book(String title, String author, String isbn, int yearPublication, List<Loan> loans) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.yearPublication = yearPublication;
        this.loans = loans;
    }
}
