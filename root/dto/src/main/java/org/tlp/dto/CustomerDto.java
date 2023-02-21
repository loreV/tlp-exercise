package org.tlp.dto;

import java.util.List;
import java.util.Objects;

public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String fiscalCode;
    private String address;
    private List<DeviceDto> associatedDevices;

    public CustomerDto(Long id, String firstName, String lastName, String fiscalCode, String address, List<DeviceDto> associatedDevices) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return id.equals(that.id) && firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) && fiscalCode.equals(that.fiscalCode) &&
                address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, fiscalCode, address);
    }
}
