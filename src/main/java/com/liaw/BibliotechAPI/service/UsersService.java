package com.liaw.BibliotechAPI.service;

import com.liaw.BibliotechAPI.dto.UsersDTO;
import com.liaw.BibliotechAPI.model.Users;
import com.liaw.BibliotechAPI.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
        Users users = dto.toEntity();
        repository.save(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    public ResponseEntity<List<UsersDTO>> searchUser(
            Long id, String cpf, String name)
    {
        Users user = new Users();
        user.setId(id);
        user.setCpf(cpf);
        user.setName(name);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Users> example = Example.of(user, matcher);
        List<Users> user_found = repository.findAll(example);
        List<UsersDTO> dto = user_found.stream()
                .map(UsersDTO::toDto).toList();

        return ResponseEntity.ok(dto);
    }


}
