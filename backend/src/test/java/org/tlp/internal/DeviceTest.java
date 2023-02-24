package org.tlp.internal;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeviceTest {
    @ParameterizedTest
    @EnumSource(DeviceStatus.class)
    void shouldGetValue(DeviceStatus anyDeviceStatus) {

        final Long id = 123L;
        final String anyUuid = "uuidzzz";
        final String anyColor = "colorz";

        final Device device = new Device(id, anyUuid, anyColor, anyDeviceStatus);

        assertEquals(id, device.getId());
        assertEquals(anyUuid, device.getUuid());
        assertEquals(anyColor, device.getColor());
        assertEquals(anyDeviceStatus, device.getStatus());
    }


    @ParameterizedTest
    @EnumSource(DeviceStatus.class)
    void shouldEqual(DeviceStatus anyDeviceStatus) {
        final Long id = 123L;
        final String anyUuid = "uuidzzz";
        final String anyColor = "colorz";

        final Device device = new Device(id, anyUuid, anyColor, anyDeviceStatus);
        final Device device2 = new Device(id, anyUuid, anyColor, anyDeviceStatus);

        assertEquals(device, device2);
        assertEquals(device.hashCode(), device2.hashCode());
    }
}