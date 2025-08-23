package com.liaw.BibliotechAPI.repository;

import com.liaw.BibliotechAPI.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long>, QueryByExampleExecutor<Users> {
}
