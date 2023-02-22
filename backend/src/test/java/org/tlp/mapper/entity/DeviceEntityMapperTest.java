package org.tlp.mapper.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tlp.entity.DeviceEntity;
import org.tlp.entity.DeviceStatusEntity;
import org.tlp.internal.Device;
import org.tlp.internal.DeviceStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeviceEntityMapperTest {

    @Mock
    private DeviceEntityStatusMapper deviceEntityStatusMapper;
    private DeviceEntityMapper sut;

    @BeforeEach
    public void setUp() {
        sut = new DeviceEntityMapper(deviceEntityStatusMapper);
    }

    @Test
    void shouldMapDeviceToDeviceEntity() {
        //given
        String expectedUuid = "anyUuid";
        String expectedColor = "anyColor";
        DeviceStatus expectedDeviceStatus = DeviceStatus.ACTIVE;

        Device device = mock(Device.class);
        when(device.getUuid()).thenReturn(expectedUuid);
        when(device.getColor()).thenReturn(expectedColor);
        when(device.getStatus()).thenReturn(expectedDeviceStatus);
        when(deviceEntityStatusMapper.mapTo(expectedDeviceStatus)).thenReturn(DeviceStatusEntity.ACTIVE);

        // when
        DeviceEntity mappedDeviceEntity = sut.mapFrom(device);

        // then
        verify(deviceEntityStatusMapper, times(1)).mapTo(DeviceStatus.ACTIVE);
        verifyNoMoreInteractions(deviceEntityStatusMapper);

        assertEquals(expectedDeviceStatus.name(), mappedDeviceEntity.getStatus().name());
        assertEquals(expectedUuid, mappedDeviceEntity.getUuid());
        assertEquals(expectedColor, mappedDeviceEntity.getColor());
        assertEquals(expectedColor, mappedDeviceEntity.getColor());
    }

    @Test
    void shouldMapDeviceEntityToDevice() {
        // given
        String expectedUuid = "anyUuid";
        String expectedColor = "anyColor";
        Long expectedId = 123L;
        DeviceStatusEntity expectedDeviceStatus = DeviceStatusEntity.ACTIVE;

        DeviceEntity device = mock(DeviceEntity.class);
        when(device.getId()).thenReturn(expectedId);
        when(device.getUuid()).thenReturn(expectedUuid);
        when(device.getColor()).thenReturn(expectedColor);
        when(device.getStatus()).thenReturn(expectedDeviceStatus);
        when(deviceEntityStatusMapper.mapFrom(expectedDeviceStatus)).thenReturn(DeviceStatus.ACTIVE);

        // when
        Device mappedDeviceEntity = sut.mapTo(device);

        // then
        verify(deviceEntityStatusMapper, times(1)).mapFrom(DeviceStatusEntity.ACTIVE);
        verifyNoMoreInteractions(deviceEntityStatusMapper);

        assertEquals(expectedId, mappedDeviceEntity.getId());
        assertEquals(expectedDeviceStatus.name(), mappedDeviceEntity.getStatus().name());
        assertEquals(expectedUuid, mappedDeviceEntity.getUuid());
        assertEquals(expectedColor, mappedDeviceEntity.getColor());
        assertEquals(expectedColor, mappedDeviceEntity.getColor());
    }

}