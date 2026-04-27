package br.com.customers.infrastructure.adapters.outbound;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    @Query("SELECT DISTINCT c FROM CustomerEntity c LEFT JOIN FETCH c.addresses")
    Page<CustomerEntity> findAllWithAddresses(Pageable pageable);

}
