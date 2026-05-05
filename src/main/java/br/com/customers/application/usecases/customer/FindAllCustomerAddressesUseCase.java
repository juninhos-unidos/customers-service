package br.com.customers.application.usecases.customer;

import br.com.customers.api.v1.model.AddressResponseDTO;

import java.util.List;

public interface FindAllCustomerAddressesUseCase {
    List<AddressResponseDTO> execute(final Long customerId);
}
