package br.com.customers.application.usecases.customer;

import br.com.customers.api.v1.model.CustomerResponseDTO;
import br.com.customers.api.v1.model.CustomerUpdateRequestDTO;

public interface UpdateCustomerUseCase {

    CustomerResponseDTO execute(final Long customerId, final CustomerUpdateRequestDTO customerRequestDTO);
}
