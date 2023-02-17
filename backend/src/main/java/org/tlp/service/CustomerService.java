package org.tlp.service;

import org.springframework.stereotype.Service;
import org.tlp.dto.CustomerDto;
import org.tlp.dto.mapper.CustomerDtoMapper;
import org.tlp.entity.mapper.CustomerEntityMapper;
import org.tlp.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoMapper customerDtoMapper;
    private final CustomerEntityMapper customerEntityMapper;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerDtoMapper customerDtoMapper,
                           CustomerEntityMapper customerEntityMapper) {
        this.customerRepository = customerRepository;
        this.customerDtoMapper = customerDtoMapper;
        this.customerEntityMapper = customerEntityMapper;
    }

    public List<CustomerDto> getAll() {
        return customerRepository.findAll().stream()
                .map(customerEntityMapper::mapTo)
                .map(customerDtoMapper::mapTo)
                .collect(Collectors.toList());
    }
}
