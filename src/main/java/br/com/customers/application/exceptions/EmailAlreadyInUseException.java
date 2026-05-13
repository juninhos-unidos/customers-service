package br.com.customers.application.exceptions;

import static java.lang.String.format;

public class EmailAlreadyInUseException extends RuntimeException {
    private static final String ERROR_MESSAGE = "email %s is already in use";

    public EmailAlreadyInUseException(String email) {
        super(format(ERROR_MESSAGE, email));
    }
}
