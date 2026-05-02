package br.com.customers.infrastructure.adapters.outbound.repositories.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birth_date;

    @Column(nullable = false, columnDefinition = "bpchar")
    private String sexo;

    @Column(nullable = false, columnDefinition = "bpchar")
    private String cpf;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private OffsetDateTime created_at;

    @Column(nullable = false)
    private OffsetDateTime updated_at;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

}
