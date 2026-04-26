package br.com.customers.application.usecases.customer.impl;

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

    @Override
    @Transactional(readOnly = true)
    public PagedCustomerResponseDTO execute(final Integer page,
            final Integer size,
            final String sort) {
        log.info("Finding all customers with page: {}, size: {}, sort: {}", page, size, sort);

        //TODO implementar

        log.info("All customers found. Total pages: {}, Total items: {}", 0, 0);
        return null;
    }
}
