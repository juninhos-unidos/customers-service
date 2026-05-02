package br.com.customers.infrastructure.adapters.outbound.repositories.entities;

import br.com.customers.api.v1.model.AddressTypeDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "addresses")
@Getter
@NoArgsConstructor
public class AddressEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @Setter
    private CustomerEntity customer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private Integer number;

    private String complement;

    @Column(name = "zip_code", nullable = false)
    private String zipcode;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AddressTypeDTO type;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(nullable = false)
    private OffsetDateTime updatedAt;

    public AddressEntity(String street, Integer number, String complement, String zipcode, String neighborhood, String city, String state, String country, AddressTypeDTO type) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.zipcode = zipcode;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.type = type;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now(ZoneOffset.UTC);
        this.updatedAt = OffsetDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now(ZoneOffset.UTC);
    }
}
