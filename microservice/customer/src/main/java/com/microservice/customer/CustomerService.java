package com.microservice.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .fistName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();

        customerRepository.save(customer);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customeId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if(fraudCheckResponse.getIsFraudster()){
            throw new IllegalStateException("Fraudster customer");
        }
    }
}
