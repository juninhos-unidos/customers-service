package br.com.customers.application.exceptions;

import static java.lang.String.format;

public class CpfAlreadyInUseException extends RuntimeException {
    private static final String ERROR_MESSAGE = "Customer registration failed: field cpf %s is already in use";

    public CpfAlreadyInUseException(final String cpf) {
        super(format(ERROR_MESSAGE, cpf));
    }
}
