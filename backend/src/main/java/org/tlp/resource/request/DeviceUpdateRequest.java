package org.tlp.resource.request;


import org.tlp.dto.DeviceStatusDto;

public class DeviceUpdateRequest {
    private DeviceStatusDto deviceStatus;
    private String color;

    public DeviceUpdateRequest() {}

    public DeviceUpdateRequest(DeviceStatusDto deviceStatus, String color) {
        this.deviceStatus = deviceStatus;
        this.color = color;
    }


    public DeviceStatusDto getDeviceStatus() {
        return deviceStatus;
    }

    public String getColor() {
        return color;
    }
}
