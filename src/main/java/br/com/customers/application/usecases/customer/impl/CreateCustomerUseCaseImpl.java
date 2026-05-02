package br.com.customers.application.usecases.customer.impl;

import br.com.customers.api.v1.model.CustomerRequestDTO;
import br.com.customers.api.v1.model.CustomerResponseDTO;
import br.com.customers.application.exceptions.CustomerAlreadyExistsException;
import br.com.customers.application.usecases.address.CreateAddressUseCase;
import br.com.customers.application.usecases.customer.CreateCustomerUseCase;
import br.com.customers.infrastructure.adapters.outbound.repositories.CustomerRepository;
import br.com.customers.infrastructure.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerMapper customerMapper;
    private final CreateAddressUseCase createAddressUseCase;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerResponseDTO execute(final CustomerRequestDTO requestDTO) {
        log.info("Creating customer");

        validate(requestDTO.getCpf(), requestDTO.getEmail());

        var customer = customerMapper.toEntity(requestDTO);
        var addresses = createAddressUseCase.execute(customer, requestDTO.getAddresses());
        customer.getAddresses().addAll(addresses);
        var savedCustomer = customerRepository.save(customer);


        log.info("Customer created with id: {}", savedCustomer.getId());

        return customerMapper.toResponse(savedCustomer);
    }

    private void validate(String cpf, String email) {
        if (customerRepository.existsByCpf(cpf)) {
            throw new CustomerAlreadyExistsException("field cpf already exists");
        }

        if (customerRepository.existsByEmail(email)) {
            throw new CustomerAlreadyExistsException("field email already exists");
        }
    }
}
