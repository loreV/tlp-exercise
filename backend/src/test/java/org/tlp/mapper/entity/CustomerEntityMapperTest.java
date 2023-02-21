package org.tlp.mapper.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tlp.entity.CustomerEntity;
import org.tlp.entity.DeviceEntity;
import org.tlp.internal.Customer;
import org.tlp.internal.Device;
import org.tlp.resource.DeviceResource;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class CustomerEntityMapperTest {

    @Mock
    DeviceEntityMapper deviceEntityMapper;

    private CustomerEntityMapper sut;

    @BeforeEach
    public void setUp() {
        sut = new CustomerEntityMapper(deviceEntityMapper);
    }

    @Test
    void shouldMapToCustomerInternal() {
        // given
        DeviceEntity deviceEntity = mock(DeviceEntity.class);
        Device expectedDevice = mock(Device.class);
        String expectedName = "myName";
        String expectedFiscalCode = "fiscalCode";
        String expectedAddress = "address";
        String expectedLastName = "lastName";
        CustomerEntity customerEntity = new CustomerEntity(expectedName,
                expectedLastName, expectedFiscalCode, expectedAddress,
                Collections.singletonList(deviceEntity));
        when(deviceEntityMapper.mapTo(deviceEntity)).thenReturn(expectedDevice);

        // when
        Customer actual = sut.mapTo(customerEntity);

        // then
        verify(deviceEntityMapper, times(1))
                .mapTo(deviceEntity);
        verifyNoMoreInteractions(deviceEntityMapper);

        assertEquals(expectedName, actual.firstName());
        assertEquals(expectedLastName, actual.lastName());
        assertEquals(expectedFiscalCode, actual.fiscalCode());
        assertEquals(expectedAddress, actual.address());
        assertThat(actual.associatedDevices(), hasItem(expectedDevice));
    }

    @Test
    void shouldMapToCustomerEntity() {
        // given
        Device device = mock(Device.class);
        DeviceEntity expectedDeviceEntity = mock(DeviceEntity.class);
        String expectedName = "myName";
        String expectedFiscalCode = "fiscalCode";
        String expectedAddress = "address";
        String expectedLastName = "lastName";
        Customer customerEntity = new Customer(1L, expectedName,
                expectedLastName, expectedFiscalCode, expectedAddress,
                Collections.singletonList(device));
        when(deviceEntityMapper.mapFrom(device)).thenReturn(expectedDeviceEntity);

        // when
        CustomerEntity actual = sut.mapFrom(customerEntity);

        // then
        verify(deviceEntityMapper, times(1))
                .mapFrom(device);
        verifyNoMoreInteractions(deviceEntityMapper);

        assertEquals(expectedName, actual.getFirstName());
        assertEquals(expectedLastName, actual.getLastName());
        assertEquals(expectedFiscalCode, actual.getFiscalCode());
        assertEquals(expectedAddress, actual.getAddress());
        assertThat(actual.getAssociatedDevices(), hasItem(expectedDeviceEntity));
    }

}