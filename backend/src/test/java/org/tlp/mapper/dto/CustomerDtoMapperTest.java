package org.tlp.mapper.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tlp.dto.CustomerDto;
import org.tlp.dto.DeviceDto;
import org.tlp.internal.Customer;
import org.tlp.internal.Device;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerDtoMapperTest {
    @Mock
    private DeviceDtoMapper deviceDtoMapperMock;
    private CustomerDtoMapper sut;

    @BeforeEach
    public void init() {
        sut = new CustomerDtoMapper(deviceDtoMapperMock);
    }

    @Test
    void shouldMapToDto() {
        // given
        Customer customer = mock(Customer.class);
        Device deviceMock = mock(Device.class);
        DeviceDto deviceDto = mock(DeviceDto.class);
        List<Device> expectedDeviceList = Collections.singletonList(
                deviceMock
        );
        Long expectedId = 123L;
        String expectedName = "myName";
        String expectedFiscalCode = "fiscalCode";
        String expectedAddress = "address";
        String expectedLastName = "lastName";
        when(customer.getId()).thenReturn(expectedId);
        when(customer.getFiscalCode()).thenReturn(expectedFiscalCode);
        when(customer.getAddress()).thenReturn(expectedAddress);
        when(customer.getFirstName()).thenReturn(expectedName);
        when(customer.getLastName()).thenReturn(expectedLastName);
        when(deviceDtoMapperMock.mapFrom(deviceMock)).thenReturn(deviceDto);
        when(customer.getAssociatedDevices()).thenReturn(expectedDeviceList);

        // when
        CustomerDto customerDto = sut.mapTo(customer);

        // then
        assertEquals(expectedId, customerDto.getId());
        assertEquals(expectedFiscalCode, customerDto.getFiscalCode());
        assertEquals(expectedAddress, customerDto.getAddress());
        assertEquals(expectedName, customerDto.getFirstName());
        assertEquals(expectedLastName, customerDto.getLastName());
        assertEquals(List.of(deviceDto), customerDto.getAssociatedDevices());
    }

    @Test
    void shouldMapToInternal() {
        // given
        CustomerDto customerDto = mock(CustomerDto.class);
        DeviceDto deviceMock = mock(DeviceDto.class);
        Device deviceDto = mock(Device.class);
        List<DeviceDto> expectedDeviceList = Collections.singletonList(
                deviceMock
        );

        Long expectedId = 123L;
        String expectedName = "myName";
        String expectedFiscalCode = "fiscalCode";
        String expectedAddress = "address";
        String expectedLastName = "lastName";
        when(customerDto.getId()).thenReturn(expectedId);
        when(customerDto.getFiscalCode()).thenReturn(expectedFiscalCode);
        when(customerDto.getAddress()).thenReturn(expectedAddress);
        when(customerDto.getFirstName()).thenReturn(expectedName);
        when(customerDto.getLastName()).thenReturn(expectedLastName);
        when(deviceDtoMapperMock.mapTo(deviceMock)).thenReturn(deviceDto);
        when(customerDto.getAssociatedDevices()).thenReturn(expectedDeviceList);

        // when
        Customer customer = sut.mapFrom(customerDto);

        // then
        assertEquals(expectedId, customer.getId());
        assertEquals(expectedFiscalCode, customer.getFiscalCode());
        assertEquals(expectedAddress, customer.getAddress());
        assertEquals(expectedName, customer.getFirstName());
        assertEquals(expectedLastName, customer.getLastName());
        assertEquals(List.of(deviceDto), customer.getAssociatedDevices());
    }
}