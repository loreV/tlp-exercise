package org.tlp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tlp.dto.DeviceDto;
import org.tlp.entity.DeviceEntity;
import org.tlp.entity.DeviceStatusEntity;
import org.tlp.exception.NotFoundItemException;
import org.tlp.mapper.dto.DeviceDtoMapper;
import org.tlp.mapper.entity.DeviceEntityMapper;
import org.tlp.repository.DeviceRepository;
import org.tlp.resource.request.DeviceUpdateRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceDtoMapper deviceDtoMapper;
    private final DeviceEntityMapper deviceEntityMapper;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository,
                         DeviceDtoMapper deviceDtoMapper,
                         DeviceEntityMapper deviceEntityMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceDtoMapper = deviceDtoMapper;
        this.deviceEntityMapper = deviceEntityMapper;
    }

    public List<DeviceDto> getAll() {
        return deviceRepository.findAll().stream()
                .map(deviceEntityMapper::mapTo)
                .map(deviceDtoMapper::mapFrom)
                .collect(Collectors.toList());
    }

    public DeviceDto update(String uuid, DeviceUpdateRequest deviceUpdateRequest) {
        DeviceEntity byUuid = deviceRepository.findByUuid(uuid).orElseThrow(NotFoundItemException::new);
        byUuid.setColor(deviceUpdateRequest.getColor());
        byUuid.setStatus(DeviceStatusEntity.valueOf(deviceUpdateRequest.getDeviceStatus().name()));
        return deviceDtoMapper.mapFrom(deviceEntityMapper.mapTo(deviceRepository.save(byUuid)));
    }

    public void delete(String uuid) {
        deviceRepository.deleteDevice(uuid);
    }

    public boolean isDeviceExisting(String uuid) {
        return deviceRepository.findByUuid(uuid).isPresent();
    }

    public DeviceDto create(DeviceDto deviceCreateRequest) {
        DeviceEntity entity = deviceEntityMapper.mapFrom(deviceDtoMapper.mapTo(deviceCreateRequest));
        return deviceDtoMapper.mapFrom(deviceEntityMapper.mapTo(deviceRepository.save(entity)));
    }
}
