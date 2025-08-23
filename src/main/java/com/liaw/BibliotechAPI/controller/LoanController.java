package com.liaw.BibliotechAPI.controller;

import com.liaw.BibliotechAPI.dto.LoanDTO;
import com.liaw.BibliotechAPI.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService service;

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(LoanDTO dto){
        return service.createLoan(dto);
    }

    @GetMapping
    public ResponseEntity<List<LoanDTO>> listLoan(){
        return service.listLoan();
    }

}
