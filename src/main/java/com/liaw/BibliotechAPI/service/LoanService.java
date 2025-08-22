package com.liaw.BibliotechAPI.service;

import com.liaw.BibliotechAPI.dto.LoanDTO;
import com.liaw.BibliotechAPI.model.Loan;
import com.liaw.BibliotechAPI.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository repository;

    public ResponseEntity<LoanDTO> createLoan(LoanDTO dto){
        Loan loan = dto.toEntity();
        repository.save(loan);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    public ResponseEntity<List<LoanDTO>> listLoan(){
        List<Loan> loan = repository.findAll();
        List<LoanDTO> dto = loan.stream()
                .map(LoanDTO::toDto).toList();
        return ResponseEntity.ok(dto);
    }
}
