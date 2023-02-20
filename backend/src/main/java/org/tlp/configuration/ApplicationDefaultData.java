package org.tlp.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tlp.entity.CustomerEntity;
import org.tlp.entity.DeviceEntity;
import org.tlp.entity.DeviceStatusEntity;
import org.tlp.repository.CustomerRepository;
import org.tlp.repository.DeviceRepository;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

@Component
public class ApplicationDefaultData {
    private final CustomerRepository customerRepository;
    private final DeviceRepository deviceRepository;
    private final boolean defaultData;

    @Autowired
    public ApplicationDefaultData(CustomerRepository customerRepository,
                                  DeviceRepository deviceRepository,
                                  @Value("${app.defaultData}") String isDefaultData) {
        this.customerRepository = customerRepository;
        this.deviceRepository = deviceRepository;
        this.defaultData = Boolean.parseBoolean(isDefaultData);
    }

    @PostConstruct
    public void init() {
        if (defaultData) {
            createStubElements();
        }
    }

    private void createStubElements() {
        deviceRepository
                .save(new DeviceEntity("1234345UXADSFD", "Red", DeviceStatusEntity.ACTIVE));
        CustomerEntity customerEntity = customerRepository.save(new CustomerEntity(
                "Mario", "Rossi", "MARIOROSSIFISCAL",
                "Via Mario Rosso 11", listOf()));
        CustomerEntity reloadedEntity = customerRepository.findById(customerEntity.getId()).get();
        reloadedEntity.setAssociatedDevices(deviceRepository.findAll());
        customerRepository.save(reloadedEntity);
    }

}
