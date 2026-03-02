package com.example.ordersphere.controller;


import com.example.ordersphere.DTO.CustomerDTO;
import com.example.ordersphere.entity.Customer;
import com.example.ordersphere.service.CustomerService;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public String updateCustomer(@RequestBody CustomerDTO dto)
    {
        return "Record updated";
    }

}

