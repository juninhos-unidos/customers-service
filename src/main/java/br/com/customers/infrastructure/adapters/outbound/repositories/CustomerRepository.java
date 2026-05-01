package br.com.customers.infrastructure.adapters.outbound.repositories;

import br.com.customers.infrastructure.adapters.outbound.repositories.entities.CustomerEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
