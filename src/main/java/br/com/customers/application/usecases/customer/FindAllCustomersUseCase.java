package br.com.customers.application.usecases.customer;

import br.com.customers.api.v1.model.PagedCustomerResponseDTO;

public interface FindAllCustomersUseCase {

    PagedCustomerResponseDTO execute(final Integer page,
            final Integer size,
            final String sort);
}
