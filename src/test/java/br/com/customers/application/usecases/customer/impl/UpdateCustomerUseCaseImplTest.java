package br.com.customers.application.usecases.customer.impl;

import br.com.customers.api.v1.model.CustomerUpdateRequestDTO;
import br.com.customers.application.exceptions.CustomerNotFoundException;
import br.com.customers.infrastructure.adapters.outbound.repositories.CustomerRepository;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.Customer;
import br.com.customers.infrastructure.mappers.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCustomerUseCaseImplTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerMapper mapper;

    @InjectMocks
    UpdateCustomerUseCaseImpl updateCustomerUseCase;


    @Test
    void register_shouldUpdateCustomerSuccesfuly() {
        Long custommerId = 1L;
        CustomerUpdateRequestDTO customerDTO = new CustomerUpdateRequestDTO().name("Maicão");

        when(customerRepository.findById(custommerId)).thenReturn(Optional.of(new Customer()));
        updateCustomerUseCase.execute(custommerId, customerDTO);

        verify(customerRepository).save(any());
    }

    @Test
    void register_ShouldThrowException_when_customerNotFound() {
        Long customerId = 100L;
        CustomerUpdateRequestDTO customerDTO = new CustomerUpdateRequestDTO().name("Maicão");

        var exception = assertThrows(CustomerNotFoundException.class, () ->
            updateCustomerUseCase.execute(customerId, customerDTO)
        );

        assertEquals("There is no customer with the id 100.", exception.getMessage());
    }

}
