package com.example.ordersphere.service;


import com.example.ordersphere.DTO.CustomerDTO;
import com.example.ordersphere.entity.Customer;
import com.example.ordersphere.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CustomerDTO custdto)
    {
        Customer customer = new Customer();
        customer.setName(custdto.getName());
        customer.setEmail(custdto.getEmail());
        customer.setPhone(custdto.getPhone());

        return customerRepository.save(customer);
    }
}

