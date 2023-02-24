package org.tlp.resource.request;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.tlp.dto.DeviceStatusDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeviceUpdateRequestTest {
    @ParameterizedTest
    @EnumSource(DeviceStatusDto.class)
    void shouldGetDeviceUpdateRequestStatusColor(DeviceStatusDto expectedDeviceStatusDto) {
        // given
        String expectedColor = "anyColor";
        DeviceUpdateRequest deviceUpdateRequest =
                new DeviceUpdateRequest(expectedDeviceStatusDto, expectedColor);

        // when-then
        assertEquals(expectedColor, deviceUpdateRequest.getColor());
        assertEquals(expectedDeviceStatusDto, deviceUpdateRequest.getDeviceStatus());
    }
}