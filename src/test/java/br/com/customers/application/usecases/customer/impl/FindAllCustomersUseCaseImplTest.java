package br.com.customers.application.usecases.customer.impl;

import br.com.customers.api.v1.model.PagedCustomerResponseDTO;
import br.com.customers.application.exceptions.InvalidParamException;
import br.com.customers.infrastructure.adapters.outbound.repositories.CustomerRepository;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.CustomerEntity;
import br.com.customers.infrastructure.mappers.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("FindAllCustomersUseCaseImpl Tests")
class FindAllCustomersUseCaseImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private FindAllCustomersUseCaseImpl useCase;

    private PagedCustomerResponseDTO pagedResponseDTO;

    @BeforeEach
    void setUp() {
        pagedResponseDTO = new PagedCustomerResponseDTO();
    }

    @Test
    @DisplayName("Should return customers with default values when all parameters are null")
    void shouldReturnCustomersWithDefaultValuesWhenParametersAreNull() {
        Page<CustomerEntity> mockPage = new PageImpl<>(new ArrayList<>(), PageRequest.of(0, 10), 0);
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(mockPage);
        when(customerMapper.toDTO(mockPage)).thenReturn(pagedResponseDTO);

        PagedCustomerResponseDTO result = useCase.execute(null, null, null);

        assertNotNull(result);
        verify(customerRepository, times(1)).findAll(any(Pageable.class));
        verify(customerMapper, times(1)).toDTO(mockPage);
    }

    @Test
    @DisplayName("Should return customers with provided page and size parameters")
    void shouldReturnCustomersWithProvidedPageAndSize() {
        Page<CustomerEntity> mockPage = new PageImpl<>(new ArrayList<>(), PageRequest.of(1, 20), 0);
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(mockPage);
        when(customerMapper.toDTO(mockPage)).thenReturn(pagedResponseDTO);

        PagedCustomerResponseDTO result = useCase.execute(1, 20, null);

        assertNotNull(result);
        verify(customerRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @DisplayName("Should return customers with custom sort by name in ascending order")
    void shouldReturnCustomersWithSortByNameAsc() {
        Page<CustomerEntity> mockPage = new PageImpl<>(new ArrayList<>(), PageRequest.of(0, 10), 0);
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(mockPage);
        when(customerMapper.toDTO(mockPage)).thenReturn(pagedResponseDTO);

        PagedCustomerResponseDTO result = useCase.execute(0, 10, "name,asc");

        assertNotNull(result);
        verify(customerRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @DisplayName("Should return customers with sort by name in descending order")
    void shouldReturnCustomersWithSortByNameDesc() {
        Page<CustomerEntity> mockPage = new PageImpl<>(new ArrayList<>(), PageRequest.of(0, 10), 0);
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(mockPage);
        when(customerMapper.toDTO(mockPage)).thenReturn(pagedResponseDTO);

        PagedCustomerResponseDTO result = useCase.execute(0, 10, "name,desc");

        assertNotNull(result);
        verify(customerRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @DisplayName("Should return customers with sort by id in ascending order")
    void shouldReturnCustomersWithSortByIdAsc() {
        Page<CustomerEntity> mockPage = new PageImpl<>(new ArrayList<>(), PageRequest.of(0, 10), 0);
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(mockPage);
        when(customerMapper.toDTO(mockPage)).thenReturn(pagedResponseDTO);

        PagedCustomerResponseDTO result = useCase.execute(0, 10, "id,asc");

        assertNotNull(result);
        verify(customerRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @DisplayName("Should return customers with sort by id in descending order")
    void shouldReturnCustomersWithSortByIdDesc() {
        Page<CustomerEntity> mockPage = new PageImpl<>(new ArrayList<>(), PageRequest.of(0, 10), 0);
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(mockPage);
        when(customerMapper.toDTO(mockPage)).thenReturn(pagedResponseDTO);

        PagedCustomerResponseDTO result = useCase.execute(0, 10, "id,desc");

        assertNotNull(result);
        verify(customerRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @DisplayName("Should handle case insensitivity for sort direction (ASC)")
    void shouldAcceptUppercaseSortDirectionAsc() {
        Page<CustomerEntity> mockPage = new PageImpl<>(new ArrayList<>(), PageRequest.of(0, 10), 0);
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(mockPage);
        when(customerMapper.toDTO(mockPage)).thenReturn(pagedResponseDTO);

        PagedCustomerResponseDTO result = useCase.execute(0, 10, "name,ASC");

        assertNotNull(result);
        verify(customerRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @DisplayName("Should handle case insensitivity for sort direction (DESC)")
    void shouldAcceptUppercaseSortDirectionDesc() {
        Page<CustomerEntity> mockPage = new PageImpl<>(new ArrayList<>(), PageRequest.of(0, 10), 0);
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(mockPage);
        when(customerMapper.toDTO(mockPage)).thenReturn(pagedResponseDTO);

        PagedCustomerResponseDTO result = useCase.execute(0, 10, "name,DESC");

        assertNotNull(result);
        verify(customerRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @DisplayName("Should handle case insensitivity for sort field (ID)")
    void shouldAcceptUppercaseSortField() {
        Page<CustomerEntity> mockPage = new PageImpl<>(new ArrayList<>(), PageRequest.of(0, 10), 0);
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(mockPage);
        when(customerMapper.toDTO(mockPage)).thenReturn(pagedResponseDTO);

        PagedCustomerResponseDTO result = useCase.execute(0, 10, "ID,asc");

        assertNotNull(result);
        verify(customerRepository, times(1)).findAll(any(Pageable.class));
    }

    @ParameterizedTest(name = "Invalid sort: {0}")
    @ValueSource(strings = {
        "invalid_sort",
        "name,invalid",
        "email,asc",
        "name",
        "name,asc,extra",
        ",asc",
        "name,"
    })
    @DisplayName("Should throw InvalidParamException for invalid sort parameters")
    void shouldThrowInvalidParamExceptionForInvalidSort(String invalidSort) {
        assertThrows(InvalidParamException.class,
            () -> useCase.execute(0, 10, invalidSort));
    }

    @Test
    @DisplayName("Should verify mapper is called with the repository result")
    void shouldVerifyMapperIsCalledWithRepositoryResult() {
        Page<CustomerEntity> mockPage = new PageImpl<>(new ArrayList<>(), PageRequest.of(0, 10), 0);
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(mockPage);
        when(customerMapper.toDTO(mockPage)).thenReturn(pagedResponseDTO);

        useCase.execute(null, null, null);

        verify(customerMapper, times(1)).toDTO(mockPage);
    }

    @Test
    @DisplayName("Should return the mapped DTO response")
    void shouldReturnMappedDTOResponse() {
        Page<CustomerEntity> mockPage = new PageImpl<>(new ArrayList<>(), PageRequest.of(0, 10), 0);
        PagedCustomerResponseDTO expectedDTO = new PagedCustomerResponseDTO();
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(mockPage);
        when(customerMapper.toDTO(mockPage)).thenReturn(expectedDTO);

        PagedCustomerResponseDTO result = useCase.execute(null, null, null);

        assertEquals(expectedDTO, result);
    }
}

