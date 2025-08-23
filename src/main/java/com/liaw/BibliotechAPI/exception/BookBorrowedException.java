package com.liaw.BibliotechAPI.exception;

public class BookBorrowedException extends RuntimeException {
    public BookBorrowedException(String message) {
        super(message);
    }
}
