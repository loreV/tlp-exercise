package org.tlp.entity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeviceEntityTest {
    @ParameterizedTest
    @EnumSource(DeviceStatusEntity.class)
    void shouldGetValue(DeviceStatusEntity anyDeviceStatus) {

        final String anyUuid = "uuidzzz";
        final String anyColor = "colorz";

        final DeviceEntity device = new DeviceEntity(anyUuid, anyColor, anyDeviceStatus);

        assertEquals(anyUuid, device.getUuid());
        assertEquals(anyColor, device.getColor());
        assertEquals(anyDeviceStatus, device.getStatus());
    }


    @ParameterizedTest
    @EnumSource(DeviceStatusEntity.class)
    void shouldEqual(DeviceStatusEntity anyDeviceStatus) {
        final String anyUuid = "uuidzzz";
        final String anyColor = "colorz";

        final DeviceEntity device = new DeviceEntity(anyUuid, anyColor, anyDeviceStatus);
        final DeviceEntity device2 = new DeviceEntity(anyUuid, anyColor, anyDeviceStatus);

        assertEquals(device, device2);
        assertEquals(device.hashCode(), device2.hashCode());
    }
}