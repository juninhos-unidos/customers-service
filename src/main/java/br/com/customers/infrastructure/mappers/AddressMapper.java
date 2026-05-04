package br.com.customers.infrastructure.mappers;

import br.com.customers.api.v1.model.AddressResponseDTO;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.AddressEntity;

public interface AddressMapper {
    AddressResponseDTO toResponseDTO(AddressEntity entity);
}


