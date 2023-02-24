package org.tlp.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Device")
@Table(name = "Device")
public class DeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String uuid;
    private String color;
    private DeviceStatusEntity status;

    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerEntity customerEntity;

    public DeviceEntity() {
    }

    public DeviceEntity(String uuid,
                        String color,
                        DeviceStatusEntity status) {
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

    public void setColor(String color) {
        this.color = color;
    }

    public void setStatus(DeviceStatusEntity status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceEntity that = (DeviceEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(uuid, that.uuid) &&
                Objects.equals(color, that.color) && status == that.status &&
                Objects.equals(customerEntity, that.customerEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, color, status, customerEntity);
    }
}
