package com.liaw.BibliotechAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    private Integer yearPublication;
    private Boolean active = true;

    @OneToMany(mappedBy = "book")
    private List<Loan> loans;

}
