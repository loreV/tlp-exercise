package org.tlp.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tlp.dto.CustomerDto;
import org.tlp.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerResource {

    private final CustomerService customerService;

    @Autowired
    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDto> get(){
        return customerService.getAll();
    }
}
