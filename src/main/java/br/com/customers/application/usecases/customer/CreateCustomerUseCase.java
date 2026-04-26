package br.com.customers.application.usecases.customer;

import br.com.customers.api.v1.model.CustomerRequestDTO;
import br.com.customers.api.v1.model.CustomerResponseDTO;

public interface CreateCustomerUseCase {

    CustomerResponseDTO execute(final CustomerRequestDTO customerRequestDTO);
}
