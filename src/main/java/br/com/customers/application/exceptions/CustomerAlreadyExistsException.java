package br.com.customers.application.exceptions;

import static java.lang.String.format;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(final String msg) {
        super(format(msg));
    }
}
