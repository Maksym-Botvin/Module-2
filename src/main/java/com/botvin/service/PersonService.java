package com.botvin.service;

import com.botvin.model.Customer;
import com.botvin.util.RandomGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PersonService {

    //PersonService - генерує випадкового покупця
    public static Customer createRandomCustomer() {
        final String id = UUID.randomUUID().toString();
        final String email = RandomGenerator.generateRandomEmail();
        final int age = RandomGenerator.generateRandomAge();
        final Customer customer = new Customer(id, email, age);
        return customer;
    }
}
