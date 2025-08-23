package com.liaw.BibliotechAPI.service;

import com.liaw.BibliotechAPI.dto.LoanDTO;
import com.liaw.BibliotechAPI.dto.UsersDTO;
import com.liaw.BibliotechAPI.exception.BookBorrowedException;
import com.liaw.BibliotechAPI.exception.BookNotFoundException;
import com.liaw.BibliotechAPI.exception.MaxLoanBookException;
import com.liaw.BibliotechAPI.exception.UserNotFoundException;
import com.liaw.BibliotechAPI.model.Book;
import com.liaw.BibliotechAPI.model.Loan;
import com.liaw.BibliotechAPI.model.Users;
import com.liaw.BibliotechAPI.repository.BookRepository;
import com.liaw.BibliotechAPI.repository.LoanRepository;
import com.liaw.BibliotechAPI.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository repository;
    private final UsersRepository usersRepository;
    private final BookRepository bookRepository;
    private static final Integer MAX_LOAN = 3;

    public ResponseEntity<LoanDTO> createLoan(LoanDTO dto){
        Users users = usersRepository.findById(dto.users().getId())
                .orElseThrow(()->new UserNotFoundException("Usuário não encontrado"));

        Book book = bookRepository.findById(dto.book().getId())
                .orElseThrow(()-> new BookNotFoundException("Livro não encontrado"));

        validateBook(book);
        validateUser(users);

        Loan loan = dto.toEntity();
        repository.save(loan);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<List<LoanDTO>> listLoan(){
        List<Loan> loan = repository.findAll();
        List<LoanDTO> dto = loan.stream()
                .map(LoanDTO::toDto).toList();
        return ResponseEntity.ok(dto);
    }

    private void validateBook(Book book){
        boolean isBookBorowed = repository.existsByBookAndActiveTrue(book);
        if (isBookBorowed){
            throw new BookBorrowedException("O livro " + book.getTitle() + "já está emprestado");
        }
    }

    private void validateUser(Users users){
        long activateUser = repository.countByUsersAndActiveTrue(users);
        if (activateUser >= MAX_LOAN){
            throw new MaxLoanBookException("Usuário possui " + activateUser + " empréstimos. Máximo permitido: " + MAX_LOAN);
        }
    }

}
