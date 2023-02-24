package org.tlp.mapper.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tlp.dto.DeviceDto;
import org.tlp.dto.DeviceStatusDto;
import org.tlp.internal.Device;
import org.tlp.internal.DeviceStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviceDtoMapperTest {
    @Mock
    private DeviceStatusDtoMapper deviceStatusDtoMapper;
    private DeviceDtoMapper sut;

    @BeforeEach
    public void init() {
        sut = new DeviceDtoMapper(deviceStatusDtoMapper);
    }


    @Test
    void shouldMapToDto() {
        // given
        Device deviceMock = mock(Device.class);
        DeviceStatus anyDeviceStatus = DeviceStatus.ACTIVE;
        DeviceStatusDto anyDeviceStatusDto = DeviceStatusDto.ACTIVE;
        String expectedColor = "anyColor";
        String expectedUuid = "anyUuid";

        when(deviceMock.getStatus()).thenReturn(anyDeviceStatus);
        when(deviceMock.getColor()).thenReturn(expectedColor);
        when(deviceMock.getUuid()).thenReturn(expectedUuid);
        when(deviceStatusDtoMapper.mapFrom(anyDeviceStatus)).thenReturn(anyDeviceStatusDto);


        // when
        DeviceDto deviceDto = sut.mapFrom(deviceMock);

        // then
        assertEquals(expectedColor, deviceDto.getColor());
        assertEquals(expectedUuid, deviceDto.getUuid());
        assertEquals(anyDeviceStatusDto, deviceDto.getStatus());
    }

    @Test
    void shouldMapToInternal() {
        // given
        DeviceDto deviceMock = mock(DeviceDto.class);
        DeviceStatusDto anyDeviceStatus = DeviceStatusDto.ACTIVE;
        DeviceStatus anyDeviceStatusDto = DeviceStatus.ACTIVE;
        String expectedColor = "anyColor";
        String expectedUuid = "anyUuid";

        when(deviceMock.getStatus()).thenReturn(anyDeviceStatus);
        when(deviceMock.getColor()).thenReturn(expectedColor);
        when(deviceMock.getUuid()).thenReturn(expectedUuid);
        when(deviceStatusDtoMapper.mapTo(anyDeviceStatus)).thenReturn(anyDeviceStatusDto);


        // when
        Device device = sut.mapTo(deviceMock);

        // then
        assertEquals(expectedColor, device.getColor());
        assertEquals(expectedUuid, device.getUuid());
        assertEquals(anyDeviceStatusDto, device.getStatus());
    }
}