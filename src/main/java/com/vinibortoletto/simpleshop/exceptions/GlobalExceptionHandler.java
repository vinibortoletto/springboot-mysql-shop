package com.vinibortoletto.simpleshop.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomException> notFound(NotFoundException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomException customException = new CustomException(
                Instant.now(),
                status.value(),
                "Not found",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(customException);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<CustomException> conflict(ConflictException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;

        CustomException customException = new CustomException(
                Instant.now(),
                status.value(),
                "Conflict",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(customException);
    }
}
