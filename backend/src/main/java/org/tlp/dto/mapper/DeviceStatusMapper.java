package org.tlp.dto.mapper;

import org.springframework.stereotype.Component;
import org.tlp.dto.DeviceStatusDto;
import org.tlp.internal.DeviceStatus;
import org.tlp.mapper.Mapper;

import java.util.Arrays;

@Component
public class DeviceStatusMapper implements Mapper<DeviceStatusDto, org.tlp.internal.DeviceStatus> {

    @Override
    public DeviceStatus mapTo(DeviceStatusDto obj) {
        return Arrays.stream(DeviceStatus.values())
                .filter(it-> it.name().equals(obj.name())).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public DeviceStatusDto mapFrom(org.tlp.internal.DeviceStatus obj) {
        return Arrays.stream(DeviceStatusDto.values())
                .filter(it-> it.name().equals(obj.name())).findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
