package org.tlp.dto;

import java.util.List;

public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String fiscalCode;
    private String address;
    private List<DeviceDto> associatedDevices;

    public CustomerDto(Long id, String firstName, String lastName, String fiscalCode,
                       String address, List<DeviceDto> associatedDevices) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fiscalCode = fiscalCode;
        this.address = address;
        this.associatedDevices = associatedDevices;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public String getAddress() {
        return address;
    }

    public List<DeviceDto> getAssociatedDevices() {
        return associatedDevices;
    }

    public Long getId() {
        return id;
    }
}
