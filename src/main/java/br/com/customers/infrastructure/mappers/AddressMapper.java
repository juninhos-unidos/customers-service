package br.com.customers.infrastructure.mappers;

import br.com.customers.api.v1.model.AddressRequestDTO;
import br.com.customers.api.v1.model.AddressResponseDTO;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressEntity toEntity(AddressRequestDTO request) {
        return new AddressEntity(
            request.getStreet(),
            request.getNumber(),
            request.getComplement(),
            request.getZipcode(),
            request.getNeighborhood(),
            request.getCity(),
            request.getState(),
            request.getCountry(),
            request.getType()
        );
    }

    public AddressResponseDTO toResponse(AddressEntity address) {
        return new AddressResponseDTO()
            .id(address.getId())
            .street(address.getStreet())
            .number(address.getNumber())
            .complement(address.getComplement())
            .zipcode(address.getZipcode())
            .neighborhood(address.getNeighborhood())
            .city(address.getCity())
            .state(address.getState())
            .country(address.getCountry())
            .type(address.getType())
            .createdAt(address.getCreatedAt())
            .updatedAt(address.getUpdatedAt());
    }
}
