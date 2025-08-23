package com.liaw.BibliotechAPI.repository;

import com.liaw.BibliotechAPI.model.Book;
import com.liaw.BibliotechAPI.model.Loan;
import com.liaw.BibliotechAPI.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
   boolean existsByBookAndActiveTrue(Book book);
   long countByUsersAndActiveTrue(Users users);
}
