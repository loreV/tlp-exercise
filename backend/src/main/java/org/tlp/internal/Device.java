package org.tlp.internal;

public record Device(
        Long id,
        String uuid,
        String color,
        DeviceStatus status
) {
}
