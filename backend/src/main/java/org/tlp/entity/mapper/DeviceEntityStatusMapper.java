package org.tlp.entity.mapper;

import org.springframework.stereotype.Component;
import org.tlp.entity.DeviceStatusEntity;
import org.tlp.internal.DeviceStatus;
import org.tlp.mapper.Mapper;

import java.util.Arrays;

@Component
public class DeviceEntityStatusMapper implements Mapper<DeviceStatus, DeviceStatusEntity> {

    @Override
    public DeviceStatusEntity mapTo(DeviceStatus obj) {
        return Arrays.stream(DeviceStatusEntity.values())
                .filter(it-> it.name().equals(obj.name())).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public DeviceStatus mapFrom(DeviceStatusEntity obj) {
        return Arrays.stream(DeviceStatus.values())
                .filter(it-> it.name().equals(obj.name())).findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
