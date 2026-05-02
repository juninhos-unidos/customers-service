package br.com.customers.application.usecases.address.impl;

import br.com.customers.api.v1.model.AddressRequestDTO;
import br.com.customers.application.usecases.address.CreateAddressUseCase;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.AddressEntity;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.CustomerEntity;
import br.com.customers.infrastructure.mappers.AddressMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateAddressUseCaseImpl implements CreateAddressUseCase {

    private final AddressMapper addressMapper;

    @Override
    public List<AddressEntity> execute(CustomerEntity customer, List<AddressRequestDTO> addressesDTO) {
        var addressesEntities = addressesDTO.stream().map(addressMapper::toEntity).toList();
        addressesEntities.forEach(address -> address.setCustomer(customer));
        return addressesEntities;
    }
}
