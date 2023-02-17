package org.tlp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uuid;
    private String color;
    private DeviceStatusEntity status;

    public DeviceEntity() {}

    public DeviceEntity(String uuid, String color, DeviceStatusEntity status) {
        this.uuid = uuid;
        this.color = color;
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public String getColor() {
        return color;
    }

    public DeviceStatusEntity getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DeviceEntity{" + "id=" + id + ", uuid='" + uuid + '\'' + ", color='" + color + '\'' + ", status=" + status + '}';
    }


}
