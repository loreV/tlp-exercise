package org.tlp.dto.mapper;

import org.springframework.stereotype.Component;
import org.tlp.dto.DeviceDto;
import org.tlp.internal.Device;
import org.tlp.mapper.Mapper;

@Component
public class DeviceDtoMapper implements Mapper<DeviceDto, Device> {

    private final DeviceStatusMapper deviceStatusMapper;

    public DeviceDtoMapper(DeviceStatusMapper deviceStatusMapper) {
        this.deviceStatusMapper = deviceStatusMapper;
    }

    @Override
    public Device mapTo(DeviceDto obj) {
        return new Device(null, obj.getUuid(),
                obj.getColor(),
                deviceStatusMapper.mapTo(obj.getStatus()));
    }
    @Override
    public DeviceDto mapFrom(Device obj) {
        return new DeviceDto(obj.uuid(), obj.color(), deviceStatusMapper.mapFrom(obj.status()));
    }
}
