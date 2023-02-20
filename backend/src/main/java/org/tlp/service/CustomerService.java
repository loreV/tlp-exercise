package org.tlp.service;

import org.springframework.stereotype.Service;
import org.tlp.dto.CustomerDto;
import org.tlp.entity.CustomerEntity;
import org.tlp.entity.DeviceEntity;
import org.tlp.exception.ForbiddenException;
import org.tlp.mapper.dto.CustomerDtoMapper;
import org.tlp.mapper.entity.CustomerEntityMapper;
import org.tlp.repository.CustomerRepository;
import org.tlp.repository.DeviceRepository;
import org.tlp.resource.request.CustomerCreateRequest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final DeviceRepository deviceRepository;
    private final CustomerDtoMapper customerDtoMapper;
    private final CustomerEntityMapper customerEntityMapper;

    public CustomerService(CustomerRepository customerRepository,
                           DeviceRepository deviceRepository,
                           CustomerDtoMapper customerDtoMapper,
                           CustomerEntityMapper customerEntityMapper) {
        this.customerRepository = customerRepository;
        this.deviceRepository = deviceRepository;
        this.customerDtoMapper = customerDtoMapper;
        this.customerEntityMapper = customerEntityMapper;
    }

    public List<CustomerDto> getAll() {
        return customerRepository.findAll().stream()
                .map(customerEntityMapper::mapTo)
                .map(customerDtoMapper::mapTo)
                .collect(Collectors.toList());
    }

    public CustomerDto updateAddressForCustomerId(Long id, String address) {
        Optional<CustomerEntity> byId = customerRepository.findById(id);
        CustomerEntity customerEntity = byId.orElseThrow();
        customerEntity.setAddress(address);
        return customerDtoMapper.mapTo(
                customerEntityMapper.mapTo(
                        customerRepository.save(customerEntity)
                )
        );
    }

    public CustomerDto create(CustomerCreateRequest createRequest) {
        CustomerEntity customerEntity =
                new CustomerEntity(createRequest.getFirstName(), createRequest.getLastName(),
                        createRequest.getFiscalCode(), createRequest.getAddress(), emptyList());
        CustomerEntity savedCustomerEntity = customerRepository.save(customerEntity);
        return customerDtoMapper.mapTo(customerEntityMapper.mapTo(savedCustomerEntity));
    }

    public CustomerDto associateDeviceToCustomer(Long id, String deviceUuid) {
        DeviceEntity deviceForAssociation = deviceRepository.findByUuid(deviceUuid);
        Optional<CustomerEntity> byId = customerRepository.findById(id);
        CustomerEntity customerEntity = byId.orElseThrow(NoSuchElementException::new);
        ensureCustomerEntityHasNoReachedDeviceAssociationLimit(customerEntity);
        customerEntity.getAssociatedDevices().add(deviceForAssociation);
        CustomerEntity save = customerRepository.save(customerEntity);
        return customerDtoMapper.mapTo(customerEntityMapper.mapTo(save));
    }

    private static void ensureCustomerEntityHasNoReachedDeviceAssociationLimit(CustomerEntity customerEntity) {
        if (customerEntity.getAssociatedDevices().size() > 1) {
            throw new ForbiddenException();
        }
    }
}
