package br.com.customers.application.usecases.customer.impl;

import br.com.customers.infrastructure.adapters.outbound.repositories.CustomerRepository;
import br.com.customers.infrastructure.mappers.CustomerMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.customers.api.v1.model.PagedCustomerResponseDTO;
import br.com.customers.application.usecases.customer.FindAllCustomersUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindAllCustomersUseCaseImpl implements FindAllCustomersUseCase {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional(readOnly = true)
    public PagedCustomerResponseDTO execute(final Integer page,
            final Integer size,
            final String sort) {
        log.info("Finding all customers with page: {}, size: {}, sort: {}", page, size, sort);

        String[] sortParts = sort.split(",");
        Sort.Direction direction = Sort.Direction.fromString(sortParts[1]);
        Sort pageSort = Sort.by(direction, sortParts[0]);
        Pageable pageable = PageRequest.of(page,size,pageSort);

        var entity = customerRepository.findAll(pageable);
        log.info("All customers found. Total pages: {}, Total items: {}", entity.getTotalPages(), entity.getTotalElements());
        return customerMapper.toDTO(entity);
    }
}
