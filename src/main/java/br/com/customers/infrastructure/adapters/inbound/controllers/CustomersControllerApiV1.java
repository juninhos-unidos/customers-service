package br.com.customers.infrastructure.adapters.inbound.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.customers.api.v1.CustomersApiV1;
import br.com.customers.api.v1.model.CustomerRequestDTO;
import br.com.customers.api.v1.model.CustomerResponseDTO;
import br.com.customers.api.v1.model.PagedCustomerResponseDTO;
import br.com.customers.application.usecases.customer.CreateCustomerUseCase;
import br.com.customers.application.usecases.customer.FindAllCustomersUseCase;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CustomersControllerApiV1 implements CustomersApiV1 {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final FindAllCustomersUseCase findAllCustomersUseCase;

    @Override
    public ResponseEntity<CustomerResponseDTO> createCustomer(final CustomerRequestDTO customerRequestDTO) {
        return ResponseEntity.status(CREATED).body(createCustomerUseCase.execute(customerRequestDTO));
    }

    @Override
    public ResponseEntity<PagedCustomerResponseDTO> findAllCustomers(final Integer page, final Integer size, final String sort) {
        return ResponseEntity.ok(findAllCustomersUseCase.execute(page, size, sort));
    }
}
