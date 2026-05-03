package br.com.customers.infrastructure.adapters.inbound.controllers;

import br.com.customers.api.v1.AddressesApiV1;
import br.com.customers.api.v1.model.AddressPatchRequestDTO;
import br.com.customers.api.v1.model.AddressResponseDTO;
import br.com.customers.application.usecases.address.UpdateAddressUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class AddressesControllerApiV1 implements AddressesApiV1 {

    final UpdateAddressUseCase updateAddressUseCase;

    @Override
    public ResponseEntity<AddressResponseDTO> updateAddressPartial(Long addressId, AddressPatchRequestDTO addressPatchRequestDTO) {
        return ResponseEntity.ok(updateAddressUseCase.execute(addressId, addressPatchRequestDTO));
    }
}
