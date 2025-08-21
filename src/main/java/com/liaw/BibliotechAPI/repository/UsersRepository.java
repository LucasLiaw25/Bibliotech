package com.liaw.BibliotechAPI.repository;

import com.liaw.BibliotechAPI.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByIdAndCpfAndName(
            Long id, String cpf, String name
    );
}
