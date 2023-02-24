package org.tlp.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="CUSTOMER_ENTITY_ID")
    private List<DeviceEntity> associatedDevices;
    private String firstName;
    private String lastName;
    private String fiscalCode;
    private String address;

    protected CustomerEntity() {
    }

    public CustomerEntity(String firstName, String lastName,
                          String fiscalCode, String address,
                          List<DeviceEntity> deviceEntities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fiscalCode = fiscalCode;
        this.address = address;
        this.associatedDevices = deviceEntities;
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

    public List<DeviceEntity> getAssociatedDevices() {
        return associatedDevices;
    }

    public void setAssociatedDevices(List<DeviceEntity> associatedDevices) {
        this.associatedDevices = associatedDevices;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(associatedDevices, that.associatedDevices)
                && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) &&
                Objects.equals(fiscalCode, that.fiscalCode) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, associatedDevices, firstName, lastName, fiscalCode, address);
    }
}
