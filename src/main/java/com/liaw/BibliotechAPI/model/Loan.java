package com.liaw.BibliotechAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Users users;

    @ManyToOne
    @JsonIgnore
    private Book book;

    @Column(nullable = false)
    private boolean returned = false;

    @Column(nullable = false)
    private boolean active = true;

    private LocalDate loanDate;
    private LocalDate returnDate;
    private BigDecimal fine = BigDecimal.ZERO;
    
    @Enumerated(EnumType.STRING)
    private Status status;


    public Loan(Long id, Users users, Book book, LocalDate loanDate, LocalDate returnDate, BigDecimal fine, Status status) {
        this.id = id;
        this.users = users;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.fine = fine;
        this.status = status;
    }
}
