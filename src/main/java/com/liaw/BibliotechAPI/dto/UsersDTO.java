package com.liaw.BibliotechAPI.dto;

import com.liaw.BibliotechAPI.model.Loan;
import com.liaw.BibliotechAPI.model.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UsersDTO(
        @NotBlank String name, @NotBlank String cpf,
        @NotBlank String email, @NotNull List<Loan> loans
) {

    public static Users toEntity(UsersDTO dto){
        return new Users(
                dto.name,
                dto.cpf,
                dto.email,
                dto.loans
        );
    }

    public static UsersDTO toDto(Users users){
        return new UsersDTO(
                users.getName(),
                users.getCpf(),
                users.getEmail(),
                users.getLoans()
        );
    }

}
