package br.com.customers.infrastructure.adapters.outbound.repositories;

import br.com.customers.infrastructure.adapters.outbound.repositories.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> { }
