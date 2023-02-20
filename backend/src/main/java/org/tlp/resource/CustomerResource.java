package org.tlp.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.tlp.dto.CustomerDto;
import org.tlp.resource.request.CustomerAddressRequest;
import org.tlp.resource.request.CustomerCreateRequest;
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
    public List<CustomerDto> get() {
        return customerService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto createCustomer(@RequestBody CustomerCreateRequest customerCreateRequest) {
        return customerService.create(customerCreateRequest);
    }

    @PutMapping("/{id}/device/{uuid}")
    public CustomerDto associateDeviceToCustomer(@PathVariable Long id, @PathVariable(name = "uuid") String uuid) {
        return customerService.associateDeviceToCustomer(id, uuid);
    }

    @PatchMapping(value = "/{id}/address", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto updateAddress(@PathVariable Long id, @RequestBody CustomerAddressRequest customerAddressRequest) {
        return customerService.updateAddressForCustomerId(id, customerAddressRequest.getAddress());
    }
}
