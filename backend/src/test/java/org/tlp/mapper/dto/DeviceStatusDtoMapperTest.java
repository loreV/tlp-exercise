package org.tlp.mapper.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.tlp.dto.DeviceStatusDto;
import org.tlp.entity.DeviceStatusEntity;
import org.tlp.internal.DeviceStatus;

import static org.hibernate.internal.util.collections.CollectionHelper.setOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeviceStatusDtoMapperTest {

    private DeviceStatusDtoMapper sut;

    @BeforeEach
    public void setUp() {
        sut = new DeviceStatusDtoMapper();
    }

    @ParameterizedTest
    @EnumSource(DeviceStatus.class)
    public void shouldMapToInternalStatus(DeviceStatus deviceStatus){
        final DeviceStatusDto mapped = sut.mapFrom(deviceStatus);
        assertTrue(setOf(DeviceStatusEntity.values()).contains(DeviceStatusEntity.valueOf(mapped.name())));
    }

    @ParameterizedTest
    @EnumSource(DeviceStatusDto.class)
    public void shouldMapFromEntityStatus(DeviceStatusDto deviceStatusDto){
        final DeviceStatus mapped = sut.mapTo(deviceStatusDto);
        assertTrue(setOf(DeviceStatus.values()).contains(DeviceStatus.valueOf(mapped.name())));
    }
}