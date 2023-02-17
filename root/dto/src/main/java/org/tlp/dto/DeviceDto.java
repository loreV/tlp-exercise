package org.tlp.dto;

public class DeviceDto {
    private final String uuid;
    private final String color;
    private final DeviceStatusDto status;

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
