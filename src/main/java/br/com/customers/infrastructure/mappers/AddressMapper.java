package br.com.customers.infrastructure.mappers;

import br.com.customers.api.v1.model.AddressRequestDTO;
import br.com.customers.api.v1.model.AddressResponseDTO;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AddressEntity toEntity(AddressRequestDTO request);

    AddressResponseDTO toResponse(AddressEntity address);
}
