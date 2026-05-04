package br.com.customers.application.usecases.customer.impl;

import br.com.customers.api.v1.model.AddressRequestDTO;
import br.com.customers.api.v1.model.CustomerRequestDTO;
import br.com.customers.api.v1.model.CustomerResponseDTO;
import br.com.customers.application.exceptions.CpfAlreadyInUseException;
import br.com.customers.application.exceptions.EmailAlreadyInUseException;
import br.com.customers.application.usecases.customer.CreateCustomerUseCase;
import br.com.customers.infrastructure.adapters.outbound.repositories.CustomerRepository;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.AddressEntity;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.CustomerEntity;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.type.StatusType;
import br.com.customers.infrastructure.mappers.AddressMapper;
import br.com.customers.infrastructure.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final AddressMapper addressMapper;

    @Override
    @Transactional
    public CustomerResponseDTO execute(final CustomerRequestDTO requestDTO) {
        log.info("Creating customer");

        validate(requestDTO.getCpf(), requestDTO.getEmail());

        var customer = customerMapper.toEntity(requestDTO);
        customer.setStatus(StatusType.PENDENTE);
        var addresses = createAddresses(customer, requestDTO.getAddresses());
        customer.getAddresses().addAll(addresses);
        var savedCustomer = customerRepository.save(customer);


        log.info("Customer created with id: {}", savedCustomer.getId());

        return customerMapper.toResponse(savedCustomer);
    }

    private void validate(String cpf, String email) {
        if (customerRepository.existsByCpf(cpf)) {
            throw new CpfAlreadyInUseException(cpf);
        }

        if (customerRepository.existsByEmail(email)) {
            throw new EmailAlreadyInUseException(email);
        }
    }

    private List<AddressEntity> createAddresses(CustomerEntity customer, List<AddressRequestDTO> addressesDTO) {
        var addressesEntities = addressesDTO.stream().map(addressMapper::toEntity).toList();
        addressesEntities.forEach(address -> address.setCustomer(customer));
        return addressesEntities;
    }
}
