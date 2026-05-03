package br.com.customers.infrastructure.mappers;

import br.com.customers.api.v1.model.CustomerRequestDTO;
import br.com.customers.api.v1.model.CustomerResponseDTO;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addresses", ignore = true) // será tratado separadamente
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CustomerEntity toEntity(CustomerRequestDTO request);

    CustomerResponseDTO toResponse(CustomerEntity customer);
}
