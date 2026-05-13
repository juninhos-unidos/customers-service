package br.com.customers.infrastructure.mappers;

import br.com.customers.api.v1.model.AddressRequestDTO;
import br.com.customers.api.v1.model.AddressResponseDTO;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AddressEntity toEntity(AddressRequestDTO request);

    @Mapping(target = "createdAt", expression = "java(toOffSetDateTime(address.getCreatedAt()))")
    @Mapping(target = "updatedAt", expression = "java(toOffSetDateTime(address.getUpdatedAt()))")
    AddressResponseDTO toResponse(AddressEntity address);

    default OffsetDateTime toOffSetDateTime(Instant instant) {
        return instant != null ? instant.atOffset(ZoneOffset.UTC) : null;
    }
}
