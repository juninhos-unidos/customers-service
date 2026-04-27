package br.com.customers.application.usecases.customer.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.customers.api.v1.model.CustomerRequestDTO;
import br.com.customers.api.v1.model.CustomerResponseDTO;
import br.com.customers.application.usecases.customer.CreateCustomerUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    @Override
    @Transactional
    public CustomerResponseDTO execute(final CustomerRequestDTO requestDTO) {
        log.info("Creating customer");

        //TODO implementar

        log.info("Customer created with id: {}", 0);
        return null;
    }
}
