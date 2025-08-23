package com.liaw.BibliotechAPI.dto;

import com.liaw.BibliotechAPI.model.Loan;
import com.liaw.BibliotechAPI.model.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record UsersDTO(
        Long id, @NotBlank String name, @CPF String cpf,
        @NotBlank String email, Boolean active, List<Loan> loans
) {

    public Users toEntity(){
        return new Users(
                this.id,
                this.name,
                this.cpf,
                this.email,
                this.active,
                this.loans
        );
    }

    public static UsersDTO toDto(Users users){
        return new UsersDTO(
                users.getId(),
                users.getName(),
                users.getCpf(),
                users.getEmail(),
                users.getActive(),
                users.getLoans()
        );
    }

}
