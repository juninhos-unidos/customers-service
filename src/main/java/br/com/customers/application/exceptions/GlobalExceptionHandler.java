package br.com.customers.application.exceptions;

import br.com.customers.api.v1.model.ErrorResponseDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        var error = new ErrorResponseDTO()
            .message("Invalid parameter");
        ex.getConstraintViolations()
            .forEach(v -> error.addDetailsItem(v.getMessage()));

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneric(Exception ex) {
        var error = new ErrorResponseDTO()
            .message("Internal server error");

        return ResponseEntity.internalServerError().body(error);
    }
}
