package org.tlp.resource.request;

public class CustomerAddressRequest {
    private String address;

    public CustomerAddressRequest() {}

    public CustomerAddressRequest(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

}
