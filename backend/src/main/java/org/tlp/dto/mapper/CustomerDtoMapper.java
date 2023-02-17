package org.tlp.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tlp.dto.CustomerDto;
import org.tlp.internal.Customer;
import org.tlp.mapper.Mapper;

import java.util.stream.Collectors;

@Component
public class CustomerDtoMapper implements Mapper<Customer, CustomerDto> {

    private final DeviceDtoMapper deviceDtoMapper;

    @Autowired
    public CustomerDtoMapper(DeviceDtoMapper deviceDtoMapper) {
        this.deviceDtoMapper = deviceDtoMapper;
    }

    @Override
    public CustomerDto mapTo(Customer obj) {
        return new CustomerDto(obj.firstName(), obj.lastName(),
                obj.fiscalCode(), obj.address(),
                obj.associatedDevices().stream().map(deviceDtoMapper::mapFrom).collect(Collectors.toList()));
    }

    @Override
    public Customer mapFrom(CustomerDto obj) {
        return new Customer(null, obj.getFirstName(), obj.getLastName(),
                obj.getFiscalCode(), obj.getAddress(),
                obj.getAssociatedDevices().stream().map(deviceDtoMapper::mapTo).collect(Collectors.toList()));
    }
}
