package org.tlp.entity;

import jakarta.persistence.*;

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

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    @Override
    public String toString() {
        return "DeviceEntity{" + "id=" + id + ", uuid='" + uuid + '\'' + ", color='" + color + '\'' + ", status=" + status + '}';
    }


}
