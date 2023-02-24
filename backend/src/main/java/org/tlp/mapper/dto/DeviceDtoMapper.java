package org.tlp.mapper.dto;

import org.springframework.stereotype.Component;
import org.tlp.dto.DeviceDto;
import org.tlp.internal.Device;
import org.tlp.mapper.Mapper;

@Component
public class DeviceDtoMapper implements Mapper<DeviceDto, Device> {

    private final DeviceStatusDtoMapper deviceStatusDtoMapper;

    public DeviceDtoMapper(DeviceStatusDtoMapper deviceStatusDtoMapper) {
        this.deviceStatusDtoMapper = deviceStatusDtoMapper;
    }

    @Override
    public Device mapTo(DeviceDto obj) {
        return new Device(null, obj.getUuid(),
                obj.getColor(),
                deviceStatusDtoMapper.mapTo(obj.getStatus()));
    }
    @Override
    public DeviceDto mapFrom(Device obj) {
        return new DeviceDto(obj.getUuid(), obj.getColor(), deviceStatusDtoMapper.mapFrom(obj.getStatus()));
    }
}
