package br.com.customers.infrastructure.adapters.outbound.repositories.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    private String complement;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, columnDefinition = "bpchar")
    private String state;

    @Column(name = "zip_code", nullable = false, columnDefinition = "bpchar")
    private String zip_code;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private OffsetDateTime created_at;

    @Column(nullable = false)
    private OffsetDateTime updated_at;
}
