package br.com.customers.application.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(final String message) {
        super(message);
    }

    public CustomerNotFoundException(final Long customerId) {
        this(String.format("There is no customer with the id %d.", customerId));
    }

}
