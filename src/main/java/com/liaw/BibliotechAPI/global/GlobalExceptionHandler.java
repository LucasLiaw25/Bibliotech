package com.liaw.BibliotechAPI.global;

import com.liaw.BibliotechAPI.exception.ErrorMessage;
import com.liaw.BibliotechAPI.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ErrorMessage handleUserNotFoundException(UserNotFoundException e){
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(),
                "Usuário não encontrado",
                List.of()
                );
    }

}
