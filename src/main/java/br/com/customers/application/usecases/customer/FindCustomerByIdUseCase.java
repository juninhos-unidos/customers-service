package br.com.customers.application.usecases.customer;
import br.com.customers.api.v1.model.CustomerResponseDTO;

public interface FindCustomerByIdUseCase {
    CustomerResponseDTO execute(String id);

}
