package br.com.customers.application.usecases.address.impl;

import br.com.customers.api.v1.model.AddressPatchRequestDTO;
import br.com.customers.api.v1.model.AddressResponseDTO;
import br.com.customers.application.exceptions.AddressNotFoundException;
import br.com.customers.infrastructure.adapters.outbound.repositories.AddressRepository;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.AddressEntity;
import br.com.customers.infrastructure.mappers.AddressMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateAddressUseCaseImplTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private UpdateAddressUseCaseImpl updateAddressUseCase;

    @Test
    @DisplayName("Should throw AddressNotFoundException when address ID doesn't exist")
    void shouldThrowExceptionWhenAddressNotFound() {

        Long invalidId = 666L;
        AddressPatchRequestDTO requestDTO = new AddressPatchRequestDTO();

        when(addressRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> updateAddressUseCase.execute(invalidId, requestDTO))
            .isInstanceOf(AddressNotFoundException.class);

        verifyNoInteractions(addressMapper);
    }

    @Test
    @DisplayName("Should update address successfully")
    void shouldUpdateAddressSuccessfully() {

        Long addressId = 1L;

        AddressEntity dataBaseEntity = AddressEntity.builder()
            .id(addressId)
            .street("old street")
            .zipCode("12345678")
            .build();

        AddressPatchRequestDTO patchRequestDTO = new AddressPatchRequestDTO();
        patchRequestDTO.setStreet("new street");

        when(addressRepository.save(dataBaseEntity)).thenReturn(dataBaseEntity);
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(dataBaseEntity));
        when(addressMapper.toResponseDTO(dataBaseEntity)).thenReturn(new AddressResponseDTO());

        updateAddressUseCase.execute(addressId, patchRequestDTO);

        assertThat(dataBaseEntity.getStreet()).isEqualTo(patchRequestDTO.getStreet());
        assertThat(dataBaseEntity.getZipCode()).isEqualTo("12345678");
        assertThat(dataBaseEntity.getUpdatedAt()).isNotNull();

    }

    @Test
    @DisplayName("Should throw an HTTP 500 Internal Server Error when database fails")
    void shouldThrowExceptionWhenDatabaseFails() {
        Long addressId = 1L;
        AddressPatchRequestDTO requestDTO = new AddressPatchRequestDTO();

        when(addressRepository.findById(addressId)).thenThrow(new RuntimeException("Internal server error"));

        assertThatThrownBy(() -> updateAddressUseCase.execute(addressId, requestDTO))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("Internal server error");

        verifyNoInteractions(addressMapper);
    }
}
