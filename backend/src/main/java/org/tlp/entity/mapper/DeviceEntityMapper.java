package org.tlp.entity.mapper;

import org.tlp.entity.DeviceEntity;
import org.tlp.internal.Device;
import org.tlp.mapper.Mapper;

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
        return new DeviceEntity(obj.uuid(), obj.color(),
                deviceEntityStatusMapper.mapTo(obj.status()));
    }
}
