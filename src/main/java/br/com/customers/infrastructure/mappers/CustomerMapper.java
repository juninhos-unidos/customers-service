package br.com.customers.infrastructure.mappers;

import br.com.customers.api.v1.model.CustomerRequestDTO;
import br.com.customers.api.v1.model.CustomerResponseDTO;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;


@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addresses", ignore = true) // será tratado separadamente
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CustomerEntity toEntity(CustomerRequestDTO request);


    @Mapping(target = "createdAt", expression = "java(toOffSetDateTime(customer.getCreatedAt()))")
    @Mapping(target = "updatedAt", expression = "java(toOffSetDateTime(customer.getUpdatedAt()))")
    CustomerResponseDTO toResponse(CustomerEntity customer);

    default OffsetDateTime toOffSetDateTime(Instant instant) {
        return instant != null ? instant.atOffset(ZoneOffset.UTC) : null;
    }
}
