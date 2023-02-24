package org.tlp.service.provider;

import org.junit.jupiter.api.Test;
import org.tlp.entity.CustomerEntity;
import org.tlp.entity.DeviceEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class CustomerEntityProviderTest {
    @Test
    void shouldProviderCustomerEntity() {
        // given
        String expectedName = "name";
        String expectedLastName = "lastName";
        String expectedFiscalCode = "fiscalCode";
        String expectedAddress = "address";
        List<DeviceEntity> deviceList = List.of(mock(DeviceEntity.class));

        // when
        final CustomerEntity customerEntity = new CustomerEntityProvider().provideCustomerEntity(
                expectedName, expectedLastName, expectedFiscalCode,
                expectedAddress, deviceList);

        // then
        assertEquals(expectedName, customerEntity.getFirstName());
        assertEquals(expectedLastName, customerEntity.getLastName());
        assertEquals(expectedFiscalCode, customerEntity.getFiscalCode());
        assertEquals(expectedAddress, customerEntity.getAddress());
        assertEquals(deviceList, customerEntity.getAssociatedDevices());
    }
}