package org.tlp.entity.mapper;

import org.springframework.stereotype.Component;
import org.tlp.entity.CustomerEntity;
import org.tlp.internal.Customer;
import org.tlp.mapper.Mapper;

import static java.util.stream.Collectors.toList;

@Component
public class CustomerEntityMapper implements Mapper<CustomerEntity, Customer> {

    private final DeviceEntityMapper deviceEntityMapper;

    public CustomerEntityMapper(DeviceEntityMapper deviceEntityMapper) {
        this.deviceEntityMapper = deviceEntityMapper;
    }

    @Override
    public Customer mapTo(CustomerEntity obj) {
        return new Customer(obj.getId(), obj.getFirstName(), obj.getLastName(), obj.getFiscalCode(), obj.getAddress(),
                obj.getAssociatedDevices().stream().map(deviceEntityMapper::mapTo).collect(toList()));
    }

    @Override
    public CustomerEntity mapFrom(Customer obj) {
        return new CustomerEntity(obj.firstName(), obj.lastName(), obj.fiscalCode(), obj.address(),
                obj.associatedDevices().stream().map(deviceEntityMapper::mapFrom).collect(toList()));
    }
}
