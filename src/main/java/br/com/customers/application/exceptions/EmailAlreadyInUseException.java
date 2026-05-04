package br.com.customers.application.exceptions;

import java.util.Set;

import static java.lang.String.format;

public class EmailAlreadyInUseException extends CustomerConflictException {
    private static final String ERROR_MESSAGE = "field email %s is already in use";

    public EmailAlreadyInUseException(String email) {
        super(Set.of(format(ERROR_MESSAGE, email)));
    }
}
