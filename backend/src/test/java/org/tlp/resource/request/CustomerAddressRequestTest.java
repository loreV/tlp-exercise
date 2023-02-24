package org.tlp.resource.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerAddressRequestTest {

    @Test
    void shouldGetAddressRequestValue() {
        // given
        final String expectedAddress = "myAddress";
        CustomerAddressRequest addressRequest = new CustomerAddressRequest(expectedAddress);

        // when
        final String actual = addressRequest.getAddress();

        // then
        assertEquals(expectedAddress, actual);
    }
}