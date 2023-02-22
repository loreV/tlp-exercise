package org.tlp.mapper.entity;

import org.springframework.stereotype.Component;
import org.tlp.entity.DeviceEntity;
import org.tlp.internal.Device;
import org.tlp.mapper.Mapper;

@Component
public class DeviceEntityMapper implements Mapper<DeviceEntity, Device> {

    private final DeviceEntityStatusMapper deviceEntityStatusMapper;

    public DeviceEntityMapper(DeviceEntityStatusMapper deviceEntityStatusMapper) {
        this.deviceEntityStatusMapper = deviceEntityStatusMapper;
    }

    @Override
    public Device mapTo(DeviceEntity obj) {
        return new Device(obj.getId(), obj.getUuid(), obj.getColor(),
                deviceEntityStatusMapper.mapFrom(obj.getStatus()));
    }

    @Override
    public DeviceEntity mapFrom(Device obj) {
        return new DeviceEntity(obj.getUuid(), obj.getColor(),
                deviceEntityStatusMapper.mapTo(obj.getStatus()));
    }
}
