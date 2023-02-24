package org.tlp.dto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeviceDtoTest {
    @ParameterizedTest
    @EnumSource(DeviceStatusDto.class)
    void shouldGetValue(DeviceStatusDto anyDeviceStatus) {

        final String anyUuid = "uuidzzz";
        final String anyColor = "colorz";

        final DeviceDto device = new DeviceDto(anyUuid, anyColor, anyDeviceStatus);

        assertEquals(anyUuid, device.getUuid());
        assertEquals(anyColor, device.getColor());
        assertEquals(anyDeviceStatus, device.getStatus());
    }


    @ParameterizedTest
    @EnumSource(DeviceStatusDto.class)
    void shouldEqual(DeviceStatusDto anyDeviceStatus) {
        final String anyUuid = "uuidzzz";
        final String anyColor = "colorz";

        final DeviceDto device = new DeviceDto(anyUuid, anyColor, anyDeviceStatus);
        final DeviceDto device2 = new DeviceDto(anyUuid, anyColor, anyDeviceStatus);

        assertEquals(device, device2);
        assertEquals(device.hashCode(), device2.hashCode());
    }
}