package org.tlp.internal;

import java.util.List;

public record Customer(
        Long id,
        String firstName,
        String lastName,
        String fiscalCode,
        String address,
        List<Device> associatedDevices
) {
}
