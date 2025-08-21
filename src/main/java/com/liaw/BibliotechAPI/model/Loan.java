package com.liaw.BibliotechAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
}
