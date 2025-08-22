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
@Table(name = "tb_users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;
    private boolean active = true;

    @OneToMany(mappedBy = "users")
    private List<Loan> loans;

    public Users(String name, String cpf, String email, List<Loan> loans) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.loans = loans;
    }
}
