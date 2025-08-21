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
    private Users users;

    @ManyToOne
    private Book book;

    private LocalDate loanDate;
    private LocalDate returnDate;
    private BigDecimal fine = BigDecimal.ZERO;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    public Loan(@NotNull Users users, @NotNull Book book, @NotBlank LocalDate loanDate, @NotBlank LocalDate returnDate, @NotNull BigDecimal fine, @NotBlank Status status) {
    }
}
