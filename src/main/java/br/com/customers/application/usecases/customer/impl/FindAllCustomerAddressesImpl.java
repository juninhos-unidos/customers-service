package br.com.customers.application.usecases.customer.impl;

import br.com.customers.application.usecases.customer.FindAllCustomerAddresses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindAllCustomerAddressesImpl implements FindAllCustomerAddresses {

    private final CustomerRepository customerRepository;
    private final AddressMapper addressMapper;

    @Override
    @Transactional(readOnly = true)
    public List<AddressResponseDTO> execute(final Long customerId){
        CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        return customer.getAddresses().stream()
            .map(addressMapper::toResponse)
            .toList();
    }


}

