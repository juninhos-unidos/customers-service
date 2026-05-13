package br.com.customers.infrastructure.adapters.inbound.controllers.handler;

import br.com.customers.api.v1.model.ErrorResponseDTO;
import br.com.customers.application.exceptions.CollectorExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handlerBadRequest(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toSet());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(buildError("Validation failed", errors));
    }

    @ExceptionHandler(CollectorExceptions.class)
    public ResponseEntity<ErrorResponseDTO> handlerConflict(CollectorExceptions e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(buildError("Customer registration failed", e.getExceptions().stream().map(Throwable::getMessage).collect(Collectors.toSet())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handlerGenericException(Exception e) {
        log.error("Unexpected error: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(buildError("Internal server error", Set.of()));
    }

    private ErrorResponseDTO buildError(String message, Set<String> details) {
        return new ErrorResponseDTO()
            .message(message)
            .details(details);
    }
}
