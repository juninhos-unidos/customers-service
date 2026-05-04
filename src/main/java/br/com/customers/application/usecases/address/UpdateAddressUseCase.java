package br.com.customers.application.usecases.address;

import br.com.customers.api.v1.model.AddressPatchRequestDTO;
import br.com.customers.api.v1.model.AddressResponseDTO;

public interface UpdateAddressUseCase {

    AddressResponseDTO execute(
        final Long addressId,
        final AddressPatchRequestDTO addressPatchRequestDTO);
}
