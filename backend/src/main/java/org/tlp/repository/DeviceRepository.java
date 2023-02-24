package org.tlp.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tlp.entity.DeviceEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends CrudRepository<DeviceEntity, Long> {
    List<DeviceEntity> findAll();

    @Modifying
    @Transactional
    @Query("delete from Device b where b.uuid=:uuid")
    void deleteDevice(@Param("uuid") String uuid);

    Optional<DeviceEntity> findByUuid(String uuid);
    boolean existsByUuid(String uuid);
}
