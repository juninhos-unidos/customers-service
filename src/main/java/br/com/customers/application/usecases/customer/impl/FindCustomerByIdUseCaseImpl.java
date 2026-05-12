package br.com.customers.application.usecases.customer.impl;
import br.com.customers.api.v1.model.CustomerResponseDTO;
import br.com.customers.application.usecases.customer.FindCustomerByIdUseCase;
import br.com.customers.infrastructure.adapters.outbound.repositories.CustomerRepository;

public class FindCustomerByIdUseCaseImpl implements FindCustomerByIdUseCase {



    @Override
    public CustomerResponseDTO execute(String id) {
        return null;
    }
    private CustomerRepository customerRepository;
}
