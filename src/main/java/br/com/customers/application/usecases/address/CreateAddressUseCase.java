package br.com.customers.application.usecases.address;

import br.com.customers.api.v1.model.AddressRequestDTO;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.AddressEntity;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.CustomerEntity;

import java.util.List;

public interface CreateAddressUseCase {
    List<AddressEntity> execute(CustomerEntity customer, List<AddressRequestDTO> addressesRequest);
}
