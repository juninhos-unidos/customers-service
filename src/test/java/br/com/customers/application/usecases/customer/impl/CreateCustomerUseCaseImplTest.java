package br.com.customers.application.usecases.customer.impl;

import br.com.customers.api.v1.model.CustomerGenderDTO;
import br.com.customers.api.v1.model.CustomerRequestDTO;
import br.com.customers.api.v1.model.CustomerResponseDTO;
import br.com.customers.application.exceptions.CollectorExceptions;
import br.com.customers.application.exceptions.CpfAlreadyInUseException;
import br.com.customers.application.exceptions.EmailAlreadyInUseException;
import br.com.customers.infrastructure.adapters.outbound.repositories.CustomerRepository;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.CustomerEntity;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.type.StatusType;
import br.com.customers.infrastructure.mappers.AddressMapper;
import br.com.customers.infrastructure.mappers.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateCustomerUseCaseImplTest {

    @Mock
    private AddressMapper addressMapper;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CreateCustomerUseCaseImpl createCustomerUseCase;

    private CustomerRequestDTO customerRequestDTO;

    @BeforeEach
    void createCustomerRequestDTO() {
        customerRequestDTO = new CustomerRequestDTO("Mark", LocalDate.of(2024, 1, 25), CustomerGenderDTO.MASCULINO, "12345678900", "mark@email.com", "77988514780", List.of());
    }

    @Test
    void should_persistCustomer_whenExecute() {
        var customerEntity = new CustomerEntity();
        var savedCustomer = new CustomerEntity();
        savedCustomer.setId(1L);
        var expectedResponse = new CustomerResponseDTO();

        when(customerMapper.toEntity(customerRequestDTO)).thenReturn(customerEntity);
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(savedCustomer);
        when(customerMapper.toResponse(savedCustomer)).thenReturn(expectedResponse);

        var response = createCustomerUseCase.execute(customerRequestDTO);

        assertEquals(expectedResponse, response);
        assertEquals(StatusType.PENDENTE, customerEntity.getStatus());
    }

    @Test
    void should_throwCollectorExceptionsWithExceptionFromCpf_when_cpfAlreadyExists() {
        when(customerRepository.existsByCpf("12345678900")).thenReturn(true);
        var exception = assertThrows(CollectorExceptions.class, () -> createCustomerUseCase.execute(customerRequestDTO));
        verify(customerRepository, never()).save(any());
        assertTrue(exception.getExceptions().stream().anyMatch(e -> e instanceof CpfAlreadyInUseException));
    }

    @Test
    void should_throwCollectorExceptionsWithExceptionFromEmail_when_emailAlreadyExists() {
        when(customerRepository.existsByEmail("mark@email.com")).thenReturn(true);
        var exception = assertThrows(CollectorExceptions.class, () -> createCustomerUseCase.execute(customerRequestDTO));
        verify(customerRepository, never()).save(any());
        assertTrue(exception.getExceptions().stream().anyMatch(e -> e instanceof EmailAlreadyInUseException));
    }

    @Test
    void should_throwCollectorExceptionsWithExceptionFromCpfAndEmail_when_emailAndCpfAlreadyExists() {
        when(customerRepository.existsByCpf("12345678900")).thenReturn(true);
        when(customerRepository.existsByEmail("mark@email.com")).thenReturn(true);
        var exception = assertThrows(CollectorExceptions.class, () -> createCustomerUseCase.execute(customerRequestDTO));
        verify(customerRepository, never()).save(any());
        assertTrue(exception.getExceptions().stream().anyMatch(e -> e instanceof CpfAlreadyInUseException));
        assertTrue(exception.getExceptions().stream().anyMatch(e -> e instanceof EmailAlreadyInUseException));
    }


}
