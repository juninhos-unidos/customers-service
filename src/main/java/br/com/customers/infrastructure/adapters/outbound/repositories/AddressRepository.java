package br.com.customers.infrastructure.adapters.outbound.repositories;

import br.com.customers.infrastructure.adapters.outbound.repositories.entities.AddressEntity;
import java.util.Optional;

public interface AddressRepository {

    Optional<AddressEntity> findById(Long id);

    AddressEntity save(AddressEntity adressEntity);
}
