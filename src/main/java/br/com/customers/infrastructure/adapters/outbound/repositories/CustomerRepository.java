package br.com.customers.infrastructure.adapters.outbound.repositories;

import br.com.customers.infrastructure.adapters.outbound.repositories.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
