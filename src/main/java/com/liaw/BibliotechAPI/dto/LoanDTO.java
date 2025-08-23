package com.liaw.BibliotechAPI.dto;

import com.liaw.BibliotechAPI.model.Book;
import com.liaw.BibliotechAPI.model.Loan;
import com.liaw.BibliotechAPI.model.Status;
import com.liaw.BibliotechAPI.model.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LoanDTO(
        Long id, @NotNull Users users, @NotNull Book book,
        @NotBlank LocalDate loanDate, @NotBlank LocalDate returnDate,
        @NotNull BigDecimal fine, @NotBlank Status status
) {

    public Loan toEntity(){
        return new Loan(
                this.id,
                this.users,
                this.book,
                this.loanDate,
                this.returnDate,
                this.fine,
                this.status
        );
    }

    public static LoanDTO toDto(Loan loan){
        return new LoanDTO(
                loan.getId(),
                loan.getUsers(),
                loan.getBook(),
                loan.getLoanDate(),
                loan.getReturnDate(),
                loan.getFine(),
                loan.getStatus()
        );
    }

}
