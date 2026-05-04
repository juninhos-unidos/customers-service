package br.com.customers.application.usecases.address.impl;

import br.com.customers.api.v1.model.AddressPatchRequestDTO;
import br.com.customers.api.v1.model.AddressResponseDTO;
import br.com.customers.application.usecases.address.UpdateAddressUseCase;
import br.com.customers.infrastructure.adapters.outbound.repositories.AddressRepository;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.AddressEntity;
import br.com.customers.infrastructure.mappers.AddressMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateAddressUseCaseImpl implements UpdateAddressUseCase {

    private final AddressRepository repository;
    private final AddressMapper mapper;

    @Override
    @Transactional
    public AddressResponseDTO execute(Long addressId, AddressPatchRequestDTO addressPatchRequestDTO) {
        log.info("starting partial update for address ID: {}", addressId);

        AddressEntity addressEntity = repository.findById(addressId)
            .orElseThrow(() -> {
                log.warn("Address ID: {} not found in database.", addressId);
                return new ResourceNotFoundException("Address not found");
            });

        Optional.ofNullable(addressPatchRequestDTO.getStreet()).ifPresent(addressEntity::setStreet);
        Optional.ofNullable(addressPatchRequestDTO.getNumber()).ifPresent(addressEntity::setNumber);
        Optional.ofNullable(addressPatchRequestDTO.getZipcode()).ifPresent(addressEntity::setZipCode);
        Optional.ofNullable(addressPatchRequestDTO.getComplement()).ifPresent(addressEntity::setComplement);
        Optional.ofNullable(addressPatchRequestDTO.getNeighborhood()).ifPresent(addressEntity::setNeighborhood);
        Optional.ofNullable(addressPatchRequestDTO.getCity()).ifPresent(addressEntity::setCity);
        Optional.ofNullable(addressPatchRequestDTO.getState()).ifPresent(addressEntity::setState);
        Optional.ofNullable(addressPatchRequestDTO.getCountry()).ifPresent(addressEntity::setCountry);
        Optional.ofNullable(addressPatchRequestDTO.getType()).ifPresent(typeEnum -> addressEntity.setType(typeEnum.name()));

        addressEntity.setUpdatedAt(OffsetDateTime.now(ZoneOffset.UTC));

        AddressEntity savedEntity = repository.save(addressEntity);

        log.info("Address updated successfully. ID: {}", addressId);

        return mapper.toResponseDTO(savedEntity);
    }
}

