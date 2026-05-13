package br.com.customers.application.exceptions;

import lombok.Getter;

import java.util.Set;

@Getter
public class CollectorExceptions extends RuntimeException {
    private Set<RuntimeException> exceptions;

    public CollectorExceptions(Set<RuntimeException> exceptions) {
        this.exceptions = exceptions;
    }
}
