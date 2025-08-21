package com.liaw.BibliotechAPI.model;

import jakarta.persistence.*;
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
}
