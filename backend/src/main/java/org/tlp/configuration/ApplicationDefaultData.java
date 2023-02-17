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
    public ApplicationDefaultData(CustomerRepository customerRepository, DeviceRepository deviceRepository, @Value("app.defaultData:true") String isDefaultData) {
        this.customerRepository = customerRepository;
        this.deviceRepository = deviceRepository;
        this.defaultData = Boolean.getBoolean(isDefaultData);
    }

    @PostConstruct
    public void init() {
        if (defaultData) {
            DeviceEntity deviceEntity = deviceRepository.save(new DeviceEntity("1234345UXADSFD", "Red", DeviceStatusEntity.ACTIVE));
            customerRepository.save(new CustomerEntity(
                    "Mario", "Rossi", "MARIOROSSIFISCAL",
                    "Via Mario Rosso 11", listOf(deviceEntity)));
        }
    }

}
