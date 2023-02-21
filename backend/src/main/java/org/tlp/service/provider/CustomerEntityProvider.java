package org.tlp.service.provider;

import org.springframework.stereotype.Component;
import org.tlp.entity.CustomerEntity;
import org.tlp.entity.DeviceEntity;

import java.util.List;

@Component
public class CustomerEntityProvider {

    public CustomerEntity provideCustomerEntity(String name, String lastName,
                                                String fiscalCode, String address,
                                                List<DeviceEntity> associatedDevices)
    {
        return new CustomerEntity(name, lastName,
                fiscalCode, address, associatedDevices);
    }
}
