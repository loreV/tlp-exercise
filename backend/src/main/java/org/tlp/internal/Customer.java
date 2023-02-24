package org.tlp.internal;

import java.util.List;
import java.util.Objects;

public class Customer {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String fiscalCode;
    private final String address;
    private final List<Device> associatedDevices;

    public Customer(
            Long id,
            String firstName,
            String lastName,
            String fiscalCode,
            String address,
            List<Device> associatedDevices
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fiscalCode = fiscalCode;
        this.address = address;
        this.associatedDevices = associatedDevices;
    }

    public Long getId() {
        return id;
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

    public List<Device> getAssociatedDevices() {
        return associatedDevices;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Customer) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.fiscalCode, that.fiscalCode) &&
                Objects.equals(this.address, that.address) &&
                Objects.equals(this.associatedDevices, that.associatedDevices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, fiscalCode, address, associatedDevices);
    }

    @Override
    public String toString() {
        return "Customer[" +
                "id=" + id + ", " +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ", " +
                "fiscalCode=" + fiscalCode + ", " +
                "address=" + address + ", " +
                "associatedDevices=" + associatedDevices + ']';
    }

}
