package com.saiko.escuela.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.saiko.escuela.dto.ExceptionDTO;

import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionDTO.builder()
            .error("Not Found")
            .message(ex.getMessage())
            .status(HttpStatus.NOT_FOUND.value())
            .date(String.valueOf(new Date()))
            .build());
    }

    @SuppressWarnings("null")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionDTO.builder()
            .error("Bad Request")
            .message(ex.getBindingResult().getFieldError().getDefaultMessage())
            .status(HttpStatus.BAD_REQUEST.value())
            .date(String.valueOf(new Date()))
            .build());
    }

}

