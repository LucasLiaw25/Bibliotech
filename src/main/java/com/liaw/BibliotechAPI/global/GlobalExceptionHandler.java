package com.liaw.BibliotechAPI.global;

import com.liaw.BibliotechAPI.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorMessage handleUserNotFoundException(UserNotFoundException e){
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                List.of()
                );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public ErrorMessage handleBookNotFoundException(BookNotFoundException e){
        return new ErrorMessage(
                HttpStatus.NOT_EXTENDED.value(),
                e.getMessage(),
                List.of()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErrorField> fields = fieldErrors.stream().map(fe-> new ErrorField(
                fe.getField(), fe.getDefaultMessage()
        )).toList();

        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Insira um valor válido", fields);
    }

    @ExceptionHandler(MaxLoanBookException.class)
    public ErrorMessage handleMaxLoanBookException(MaxLoanBookException e){
        return new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                List.of()
        );
    }

    @ExceptionHandler(BookBorrowedException.class)
    public ErrorMessage handleBookBorrowedException(BookBorrowedException e){
        return new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                List.of()
        );
    }

}
