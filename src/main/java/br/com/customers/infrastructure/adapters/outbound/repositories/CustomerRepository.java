package br.com.customers.infrastructure.adapters.outbound.repositories;

import br.com.customers.infrastructure.adapters.outbound.repositories.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> { }
