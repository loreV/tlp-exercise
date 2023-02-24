package org.tlp.internal;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.hibernate.internal.util.collections.CollectionHelper.listOf;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void shouldGetValue() {

        final Long id = 123L;
        final String firstName = "firstName";
        final String lastName = "lastName";
        final String fiscalCode = "fiscalCode";
        final String address = "address";
        final List<Device> associatedDevices = emptyList();

        final Customer customer = new Customer(id, firstName,
                lastName, fiscalCode,
                address, associatedDevices);

        assertEquals(id, customer.getId());
        assertEquals(firstName, customer.getFirstName());
        assertEquals(lastName, customer.getLastName());
        assertEquals(fiscalCode, customer.getFiscalCode());
        assertEquals(address, customer.getAddress());
        assertEquals(associatedDevices, customer.getAssociatedDevices());
    }

    @Test
    void shouldEqual() {

        final Long id = 123L;
        final String firstName = "firstName";
        final String lastName = "lastName";
        final String fiscalCode = "fiscalCode";
        final String address = "address";
        final List<Device> associatedDevices = listOf();

        final Customer customer = new Customer(id, firstName,
                lastName, fiscalCode,
                address, associatedDevices);

        final Customer customer2 = new Customer(id, firstName,
                lastName, fiscalCode,
                address, associatedDevices);

        assertEquals(customer, customer2);
        assertEquals(customer.hashCode(), customer2.hashCode());
    }



}