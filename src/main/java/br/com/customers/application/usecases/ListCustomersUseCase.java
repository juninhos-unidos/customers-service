package br.com.customers.application.usecases;

import br.com.customers.application.exceptions.InvalidParamException;
import br.com.customers.infrastructure.adapters.outbound.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListCustomersUseCase {

    private final CustomerRepository customerRepository;

    public Page<CustomerEntity> execute(Integer page, Integer size, String sort) {
        //validation param
        Integer resolvedPage = Optional.ofNullable(page).orElse(0);
        Integer resolvedSize = Optional.ofNullable(size).orElse(10);
        String resolvedSort = Optional.ofNullable(sort).orElse("name,asc");
        if(!isValidSort(resolvedSort)) {
            throw new InvalidParamException("Invalid param format: " + resolvedSort);
        }
        //  pagination
        String[] sortParts = resolvedSort.split(",");
        Sort.Direction direction = Sort.Direction.fromString(sortParts[1]);
        Sort pageSort = Sort.by(direction, sortParts[0]);
        Pageable pageable = PageRequest.of(resolvedPage,resolvedSize,pageSort);

        return customerRepository.findAllWithAddresses(pageable);
    }

    private boolean isValidSort(String sort) {
        return sort.matches("^(?i)(id|name),(?i)(asc|desc)$");
    }

}
