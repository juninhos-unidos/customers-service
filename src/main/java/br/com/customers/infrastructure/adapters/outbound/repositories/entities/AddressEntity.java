package br.com.customers.infrastructure.adapters.outbound.repositories.entities;


import br.com.customers.infrastructure.enums.AddressType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class AddressEntity {
    @Id
    private Long id;
    private String street;
    private String number;
    private String complement;
    private String zipCode;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private AddressType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne
    private CustomerEntity customer;
}
