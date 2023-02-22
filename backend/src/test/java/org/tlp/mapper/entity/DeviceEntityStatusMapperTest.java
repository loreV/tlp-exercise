package org.tlp.mapper.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.tlp.entity.DeviceStatusEntity;
import org.tlp.internal.DeviceStatus;

import static org.hibernate.internal.util.collections.CollectionHelper.setOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeviceEntityStatusMapperTest {


    private DeviceEntityStatusMapper sut;

    @BeforeEach
    public void setUp() {
        sut = new DeviceEntityStatusMapper();
    }

    @ParameterizedTest
    @EnumSource(DeviceStatus.class)
    public void shouldMapToInternalStatus(DeviceStatus deviceStatus){
        final DeviceStatusEntity mapped = sut.mapTo(deviceStatus);
        assertTrue(setOf(DeviceStatusEntity.values()).contains(DeviceStatusEntity.valueOf(mapped.name())));
    }

    @ParameterizedTest
    @EnumSource(DeviceStatusEntity.class)
    public void shouldMapFromEntityStatus(DeviceStatusEntity deviceStatusEntity){
        final DeviceStatus mapped = sut.mapFrom(deviceStatusEntity);
        assertTrue(setOf(DeviceStatus.values()).contains(DeviceStatus.valueOf(mapped.name())));
    }
}