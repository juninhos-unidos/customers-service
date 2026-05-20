package br.com.customers.application.usecases.customer.impl;

import br.com.customers.api.v1.model.AddressResponseDTO;
import br.com.customers.api.v1.model.AddressTypeDTO;
import br.com.customers.api.v1.model.CustomerResponseDTO;
import br.com.customers.api.v1.model.StatusTypeDTO;
import br.com.customers.application.usecases.customer.FindCustomerByIdUseCase;
import br.com.customers.infrastructure.adapters.outbound.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class FindCustomerByIdUseCaseImpl implements FindCustomerByIdUseCase {

    private final CustomerRepository customerRepository;

    public FindCustomerByIdUseCaseImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

public CustomerResponseDTO execute(Long id) {
    var customer = customerRepository.findById(id).orElseThrow();

    var response = new CustomerResponseDTO();
    response.setId(customer.getId());
    response.setName(customer.getFullName());
    response.setBirthDate(customer.getBirthDate());
    response.setCpf(customer.getCpf());
    response.setEmail(customer.getEmail());
    response.setPhone(customer.getPhone());
    response.setStatus(customer.getStatus() != null
            ? StatusTypeDTO.valueOf(customer.getStatus().name())
            : null);
    response.setCreatedAt(toOffset(customer.getCreatedAt()));
    response.setUpdatedAt(toOffset(customer.getUpdatedAt()));

    var addresses = customer.getAddresses() == null
            ? Collections.<AddressResponseDTO>emptyList()
            : customer.getAddresses().stream()
                .map(address -> {
                    var dto = new AddressResponseDTO();
                    dto.setId(address.getId());
                    dto.setStreet(address.getStreet());
                    dto.setNumber(parseIntOrNull(address.getNumber()));
                    dto.setComplement(address.getComplement());
                    dto.setZipcode(address.getZipCode());
                    dto.setNeighborhood(address.getNeighborhood());
                    dto.setCity(address.getCity());
                    dto.setState(address.getState());
                    dto.setCountry(address.getCountry());
                    dto.setType(address.getType() != null
                            ? AddressTypeDTO.valueOf(address.getType().name())
                            : null);
                    dto.setCreatedAt(toOffset(address.getCreatedAt()));
                    dto.setUpdatedAt(toOffset(address.getUpdatedAt()));
                    return dto;
                })
                .collect(Collectors.toList());

    response.setAddresses(addresses);

    return response;
}

private static OffsetDateTime toOffset(java.time.LocalDateTime value) {
    return value == null ? null : value.atOffset(ZoneOffset.UTC);
}

private static Integer parseIntOrNull(String value) {
    if (value == null || value.isBlank()) {
        return null;
    }
    try {
        return Integer.valueOf(value);
    } catch (NumberFormatException ex) {
        return null;
    }
}

}
