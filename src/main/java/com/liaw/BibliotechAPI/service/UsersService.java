package com.liaw.BibliotechAPI.service;

import com.liaw.BibliotechAPI.dto.UsersDTO;
import com.liaw.BibliotechAPI.model.Users;
import com.liaw.BibliotechAPI.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository repository;

    public ResponseEntity<UsersDTO> createUser(UsersDTO dto){
        Users users = UsersDTO.toEntity(dto);
        repository.save(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    public ResponseEntity<List<UsersDTO>> listUser(){
        List<Users> users = repository.findAll();
        List<UsersDTO> dto = users.stream()
                .map(UsersDTO::toDto)
                .toList();
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<UsersDTO> searchUserId(
            Long id, String cpf, String name)
    {
        Optional<Users> user = repository.findByIdAndCpfAndName(
                id, cpf, name
        );
        if (user.isPresent()){
            Users user_found = user.get();
            UsersDTO dto = UsersDTO.toDto(user_found);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }


}
