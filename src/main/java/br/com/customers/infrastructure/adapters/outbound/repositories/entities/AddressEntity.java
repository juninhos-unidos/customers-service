package br.com.customers.infrastructure.adapters.outbound.repositories.entities;

import br.com.customers.api.v1.model.AddressTypeDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@NoArgsConstructor
public class AddressEntity {
public class AddressEntity extends Auditable {

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @Setter
    private CustomerEntity customer;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_id_seq")
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

}
