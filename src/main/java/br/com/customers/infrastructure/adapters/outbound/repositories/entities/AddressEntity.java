package br.com.customers.infrastructure.adapters.outbound.repositories.entities;

import lombok.*;

import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressEntity {
    private Long id;
    private String street;
    private Integer number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String type;
    private OffsetDateTime updatedAt;
}
