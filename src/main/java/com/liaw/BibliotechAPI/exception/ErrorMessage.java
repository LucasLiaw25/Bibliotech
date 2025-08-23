package com.liaw.BibliotechAPI.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorMessage(int status, String message, List<ErrorField> field) {

}
