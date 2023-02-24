package org.tlp.resource.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerCreateRequestTest {
    @Test
    void shouldGetCustomerAddressRequestValues() {
        // given
        String expectedName = "anyName";
        String expectedLastName = "anyAddress";
        String expectedFiscalCode = "myAddress";
        String expectedAddress = "anyAddress";
        CustomerCreateRequest customerCreateRequest =
                new CustomerCreateRequest(expectedName, expectedLastName, expectedFiscalCode, expectedAddress);

        // when-then
        assertEquals(expectedName, customerCreateRequest.getFirstName());
        assertEquals(expectedLastName, customerCreateRequest.getLastName());
        assertEquals(expectedAddress, customerCreateRequest.getAddress());
        assertEquals(expectedFiscalCode, customerCreateRequest.getFiscalCode());
    }
}