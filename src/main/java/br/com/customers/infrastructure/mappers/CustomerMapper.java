package br.com.customers.infrastructure.mappers;

import br.com.customers.api.v1.model.CustomerResponseDTO;
import br.com.customers.api.v1.model.CustomerUpdateRequestDTO;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDTO toResponse(final Customer customer);

    void toDomainObject( final CustomerUpdateRequestDTO customerUpdateRequestDTO, @MappingTarget final Customer customer);
}
