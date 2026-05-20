package br.com.customers.infrastructure.adapters.outbound.repositories.entities;


import br.com.customers.infrastructure.enums.CustomerStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
public class CustomerEntity {
    @Id
    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private String cpf;
    private String email;
    private String phone;
    private CustomerStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "customer")
    private List<AddressEntity> addresses;


}
