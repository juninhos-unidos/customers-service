package br.com.customers.application.exceptions;

import br.com.customers.api.v1.model.ErrorResponseDTO;
import br.com.customers.application.exceptions.custom.InvalidParamException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidParamException.class)
    public ResponseEntity<Object> handleInvalidParam(InvalidParamException ex) {
        log.warn("Invalid param: {}", ex.getMessage());
        var error = new ErrorResponseDTO()
            .message("Invalid parameter format")
            .addDetailsItem(ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        log.warn("Constraint violation: {}", ex.getMessage());
        var error = new ErrorResponseDTO()
            .message("Invalid parameter");
        ex.getConstraintViolations()
            .forEach(v -> error.addDetailsItem(v.getMessage()));
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneric(Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        var error = new ErrorResponseDTO().message("Internal server error");
        return ResponseEntity.internalServerError().body(error);
    }
}
