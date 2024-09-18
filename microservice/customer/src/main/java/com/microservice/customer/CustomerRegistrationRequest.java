package com.microservice.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegistrationRequest{
        private String firstName;
        private String lastName;
        private String email;
}
