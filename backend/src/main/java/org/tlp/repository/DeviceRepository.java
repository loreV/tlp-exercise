package org.tlp.repository;

import org.springframework.data.repository.CrudRepository;
import org.tlp.entity.DeviceEntity;

public interface DeviceRepository extends CrudRepository<DeviceEntity, Long> {
}
