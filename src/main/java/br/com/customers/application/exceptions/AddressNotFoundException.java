package br.com.customers.application.exceptions;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException() {
    }

    public AddressNotFoundException(String message) {
        super(message);
    }
}
