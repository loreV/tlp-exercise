package org.tlp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tlp.dto.DeviceDto;
import org.tlp.entity.DeviceEntity;
import org.tlp.exception.NotFoundItemException;
import org.tlp.mapper.dto.DeviceDtoMapper;
import org.tlp.mapper.dto.DeviceStatusDtoMapper;
import org.tlp.mapper.entity.DeviceEntityMapper;
import org.tlp.mapper.entity.DeviceEntityStatusMapper;
import org.tlp.repository.DeviceRepository;
import org.tlp.resource.request.DeviceUpdateRequest;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceDtoMapper deviceDtoMapper;
    private final DeviceEntityMapper deviceEntityMapper;
    private final DeviceEntityStatusMapper deviceStatusEntityMapper;
    private final DeviceStatusDtoMapper deviceStatusDtoMapper;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository,
                         DeviceDtoMapper deviceDtoMapper,
                         DeviceEntityMapper deviceEntityMapper,
                         DeviceEntityStatusMapper deviceStatusEntityMapper,
                         DeviceStatusDtoMapper deviceStatusDtoMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceDtoMapper = deviceDtoMapper;
        this.deviceEntityMapper = deviceEntityMapper;
        this.deviceStatusEntityMapper = deviceStatusEntityMapper;
        this.deviceStatusDtoMapper = deviceStatusDtoMapper;
    }

    public List<DeviceDto> getAll() {
        return deviceRepository.findAll().stream()
                .map(deviceEntityMapper::mapTo)
                .map(deviceDtoMapper::mapFrom)
                .collect(toList());
    }

    public DeviceDto update(String uuid, DeviceUpdateRequest deviceUpdateRequest) {
        DeviceEntity byUuid = deviceRepository.findByUuid(uuid).orElseThrow(NotFoundItemException::new);
        byUuid.setColor(deviceUpdateRequest.getColor());
        byUuid.setStatus(deviceStatusEntityMapper.mapTo(
                deviceStatusDtoMapper.mapTo(
                        deviceUpdateRequest.getDeviceStatus()
                )
        ));
        return deviceDtoMapper.mapFrom(deviceEntityMapper.mapTo(deviceRepository.save(byUuid)));
    }

    public void delete(String uuid) {
        deviceRepository.deleteDevice(uuid);
    }

    public boolean doesDeviceExist(String uuid) {
        return deviceRepository.existsByUuid(uuid);
    }

    public DeviceDto create(DeviceDto deviceCreateRequest) {
        DeviceEntity entity = deviceEntityMapper.mapFrom(deviceDtoMapper.mapTo(deviceCreateRequest));
        return deviceDtoMapper.mapFrom(deviceEntityMapper.mapTo(deviceRepository.save(entity)));
    }
}
