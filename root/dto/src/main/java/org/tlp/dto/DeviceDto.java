package org.tlp.dto;

import java.util.Objects;

public class DeviceDto {
    private final String uuid;
    private final String color;
    private final DeviceStatusDto status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDto deviceDto = (DeviceDto) o;
        return uuid.equals(deviceDto.uuid) &&
                color.equals(deviceDto.color)
                && status == deviceDto.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, color, status);
    }

    public DeviceDto(String uuid, String color, DeviceStatusDto status) {
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

    public DeviceStatusDto getStatus() {
        return status;
    }
}
