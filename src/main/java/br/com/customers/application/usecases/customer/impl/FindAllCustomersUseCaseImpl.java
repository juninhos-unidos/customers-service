package br.com.customers.application.usecases.customer.impl;

import br.com.customers.application.exceptions.InvalidParamException;
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

import java.util.Optional;

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

        //validation param
        Integer resolvedPage = Optional.ofNullable(page).orElse(0);
        Integer resolvedSize = Optional.ofNullable(size).orElse(10);
        String resolvedSort = Optional.ofNullable(sort).orElse("name,asc");
        if(!isValidSort(resolvedSort)) {
            throw new InvalidParamException(resolvedSort);
        }
        //  pagination
        String[] sortParts = resolvedSort.split(",");
        Sort.Direction direction = Sort.Direction.fromString(sortParts[1]);
        Sort pageSort = Sort.by(direction, sortParts[0]);
        Pageable pageable = PageRequest.of(resolvedPage,resolvedSize,pageSort);

        var entity = customerRepository.findAll(pageable);
        log.info("All customers found. Total pages: {}, Total items: {}", entity.getTotalPages(), entity.getTotalElements());
        return customerMapper.toDTO(entity);
    }

    private boolean isValidSort(String sort) {
        return sort.matches("^(?i)(id|name),(?i)(asc|desc)$");
    }
}
