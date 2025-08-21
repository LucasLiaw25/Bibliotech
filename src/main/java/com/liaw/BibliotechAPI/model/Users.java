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

    public Users(@NotBlank String name, @NotBlank String cpf, @NotBlank String email, @NotNull List<Loan> loans) {
    }
}
