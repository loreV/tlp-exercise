package org.tlp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<DeviceEntity> associatedDevices;
    private String firstName;
    private String lastName;
    private String fiscalCode;
    private String address;

    protected CustomerEntity() {}

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

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fiscalCode='" + fiscalCode + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
