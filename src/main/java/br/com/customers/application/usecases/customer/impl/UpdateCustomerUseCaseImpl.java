package br.com.customers.application.usecases.customer.impl;

import br.com.customers.api.v1.model.CustomerResponseDTO;
import br.com.customers.api.v1.model.CustomerUpdateRequestDTO;
import br.com.customers.application.exceptions.CustomerNotFoundException;
import br.com.customers.application.usecases.customer.UpdateCustomerUseCase;
import br.com.customers.infrastructure.adapters.outbound.repositories.CustomerRepository;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.Customer;
import br.com.customers.infrastructure.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final CustomerMapper mapper;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerResponseDTO execute(final Long customerId, final CustomerUpdateRequestDTO customerRequestDTO) {
        log.info("Starting update process for customerID: {}", customerId);

        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new CustomerNotFoundException(customerId));

        mapper.toDomainObject(customerRequestDTO, customer);

        customerRepository.save(customer);

        log.info("Customer ID: {} successfully updated.", customerId);
        return mapper.toResponse(customer);
    }

}
