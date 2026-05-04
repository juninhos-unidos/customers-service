package br.com.customers.infrastructure.adapters.inbound.controllers;

import br.com.customers.api.v1.model.AddressPatchRequestDTO;
import br.com.customers.application.usecases.address.UpdateAddressUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AddressesControllerApiV1.class)
class AddressesControllerApiV1Test {

    @Autowired
    private MockMvc mockMvc; // simula a internet

    // transforma um Objeto Java em texto JSON (Serialização),
    // e transforma texto JSON em Objeto Java (Desserialização).
    @Autowired
    private ObjectMapper objectMapper; //

    @MockitoBean
    UpdateAddressUseCase updateAddressUseCase;

    @SneakyThrows
    @Test
    @DisplayName("Should return 400 bad request when zipcode contains letters")
    void shouldReturn400WhenZipcodeIsInvalid() {

        Long addressId = 1L;
        AddressPatchRequestDTO requestDTO = new AddressPatchRequestDTO();
        requestDTO.setZipcode("ZIPCODELETTERS");

        String json = objectMapper.writeValueAsString(requestDTO);

        mockMvc.perform(patch("/api/v1/addresses/{addressId}", addressId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
        ).andExpect(status().isBadRequest());
    }
}
