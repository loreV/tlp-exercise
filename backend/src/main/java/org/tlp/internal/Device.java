package org.tlp.internal;

import java.util.Objects;

public class Device {
    private final Long id;
    private final String uuid;
    private final String color;
    private final DeviceStatus status;

    public Device(
            Long id,
            String uuid,
            String color,
            DeviceStatus status
    ) {
        this.id = id;
        this.uuid = uuid;
        this.color = color;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getColor() {
        return color;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Device) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.uuid, that.uuid) &&
                Objects.equals(this.color, that.color) &&
                Objects.equals(this.status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, color, status);
    }

    @Override
    public String toString() {
        return "Device[" +
                "id=" + id + ", " +
                "uuid=" + uuid + ", " +
                "color=" + color + ", " +
                "status=" + status + ']';
    }

}
