package com.liaw.BibliotechAPI.repository;

import com.liaw.BibliotechAPI.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
