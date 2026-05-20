package br.com.customers.infrastructure.mappers;

import br.com.customers.api.v1.model.CustomerResponseDTO;
import br.com.customers.api.v1.model.PagedCustomerResponseDTO;
import br.com.customers.infrastructure.adapters.outbound.repositories.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDTO toDTO(CustomerEntity entity);

    default PagedCustomerResponseDTO toDTO(Page<CustomerEntity> page) {
        PagedCustomerResponseDTO dto = new PagedCustomerResponseDTO();
        dto.setPage(
            page.getContent()
                .stream()
                .map(this::toDTO)
                .toList()
        );
        dto.setPageNumber(page.getNumber());
        dto.setPageItems(page.getNumberOfElements());
        dto.setTotalPages(page.getTotalPages());
        dto.setTotalItems(page.getTotalElements());
        return dto;
    }

}
