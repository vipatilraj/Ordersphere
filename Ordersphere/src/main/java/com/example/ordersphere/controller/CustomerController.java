package com.example.ordersphere.controller;


import com.example.ordersphere.DTO.CustomerDTO;
import com.example.ordersphere.entity.Customer;
import com.example.ordersphere.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody CustomerDTO dto)
    {
        return customerService.createCustomer(dto);

    }
}

