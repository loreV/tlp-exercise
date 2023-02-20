package org.tlp.resource.request;

public class CustomerCreateRequest {

    private String firstName;
    private String lastName;
    private String fiscalCode;
    private String address;

    public CustomerCreateRequest() {}

    public CustomerCreateRequest(String firstName, String lastName, String fiscalCode, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fiscalCode = fiscalCode;
        this.address = address;
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
}
