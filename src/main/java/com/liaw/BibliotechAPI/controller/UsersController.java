package com.liaw.BibliotechAPI.controller;

import com.liaw.BibliotechAPI.dto.UsersDTO;
import com.liaw.BibliotechAPI.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService service;

    @PostMapping
    public ResponseEntity<UsersDTO> createUser(@RequestBody @Valid UsersDTO dto){
        return service.createUser(dto);
    }

    @GetMapping()
    public ResponseEntity<List<UsersDTO>> searchUser(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String name
    ){
        return service.searchUser(id, cpf, name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return service.deleteUser(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsersDTO> activateUser(@PathVariable Long id){
        return service.activateUser(id);
    }
}
