package br.com.customers.application.exceptions;

import java.util.Set;

import static java.lang.String.format;

public class CpfAlreadyInUseException extends CustomerConflictException {
    private static final String ERROR_MESSAGE = "field cpf %s is already in use";

    public CpfAlreadyInUseException(final String cpf) {
        super(Set.of(format(ERROR_MESSAGE, cpf)));
    }
}
