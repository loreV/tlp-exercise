package org.tlp.resource;


import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get all customers")
    public List<CustomerDto> get() {
        return customerService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a customer with no associated devices")
    public CustomerDto create(@RequestBody CustomerCreateRequest customerCreateRequest) {
        return customerService.create(customerCreateRequest);
    }

    @PutMapping(value = "/{id}/device/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Associates a device by Uuid to customer by Id")
    public CustomerDto associateDeviceToCustomer(@PathVariable Long id, @PathVariable(name = "uuid") String uuid) {
        return customerService.associateDeviceToCustomer(id, uuid);
    }

    @PatchMapping(value = "/{id}/address", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a customer address by customer Id")
    public CustomerDto updateAddress(@PathVariable Long id, @RequestBody CustomerAddressRequest customerAddressRequest) {
        return customerService.updateAddressForCustomerId(id, customerAddressRequest.getAddress());
    }
}
