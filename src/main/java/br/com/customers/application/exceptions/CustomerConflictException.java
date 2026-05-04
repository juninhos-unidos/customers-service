package br.com.customers.application.exceptions;

import lombok.Getter;

import java.util.Set;

@Getter
public class CustomerConflictException extends RuntimeException {
    private final Set<String> conflicts;

    public CustomerConflictException(Set<String> conflicts) {
        this.conflicts = conflicts;
    }

}
