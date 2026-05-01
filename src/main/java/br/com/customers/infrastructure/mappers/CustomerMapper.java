package br.com.customers.infrastructure.mappers;

import br.com.customers.api.v1.model.CustomerRequestDTO;
import br.com.customers.api.v1.model.CustomerResponseDTO;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final AddressMapper addressMapper;

    public CustomerEntity toEntity(CustomerRequestDTO request) {
        return new CustomerEntity(
            request.getName(),
            request.getBirthDate(),
            request.getCpf(),
            request.getEmail(),
            request.getPhone(),
            request.getStatus()
        );
    }

    public CustomerResponseDTO toResponse(CustomerEntity customer) {
        return new CustomerResponseDTO()
            .id(customer.getId())
            .name(customer.getName())
            .birthDate(customer.getBirthDate())
            .cpf(customer.getCpf())
            .email(customer.getEmail())
            .phone(customer.getPhone())
            .createdAt(customer.getCreatedAt())
            .updatedAt(customer.getUpdatedAt())
            .addresses(customer.getAddresses().stream().map(addressMapper::toResponse).toList());
    }
}
