package com.liaw.BibliotechAPI.dto;

import com.liaw.BibliotechAPI.model.Loan;
import com.liaw.BibliotechAPI.model.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record UsersDTO(
        @NotBlank String name, @CPF String cpf,
        @NotBlank String email, List<Loan> loans
) {

    public Users toEntity(){
        return new Users(
                this.name,
                this.cpf,
                this.email,
                this.loans
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
