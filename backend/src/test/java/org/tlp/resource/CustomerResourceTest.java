package org.tlp.resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tlp.dto.CustomerDto;
import org.tlp.resource.request.CustomerAddressRequest;
import org.tlp.resource.request.CustomerCreateRequest;
import org.tlp.service.CustomerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerResourceTest {
    @Mock private CustomerService customerServiceMock;

    private CustomerResource sut;

    @BeforeEach
    public void setUp() {
        sut = new CustomerResource(customerServiceMock);
    }

    @Test
    void shouldGetAllCustomers() {
        // given
        List<CustomerDto> anyExpectedList = Mockito.mock(List.class);
        when(customerServiceMock.getAll()).thenReturn(anyExpectedList);

        // when
        List<CustomerDto> actual = sut.get();

        // then
        verify(customerServiceMock, times(1)).getAll();
        verifyNoMoreInteractions(customerServiceMock);
        assertEquals(anyExpectedList, actual);
    }

    @Test
    void onProvidingUpdateAddress_shouldInteractWithCollaborators() {
        // given
        Long anyExpectedId = 123L;
        String anyExpectedAddress = "any expected address";
        CustomerAddressRequest customerAddressRequest = new CustomerAddressRequest(anyExpectedAddress);
        CustomerDto returnedCustomer = mock(CustomerDto.class);

        when(customerServiceMock.updateAddressForCustomerId(anyExpectedId, anyExpectedAddress))
                .thenReturn(returnedCustomer);


        // when
        CustomerDto actual = sut.updateAddress(anyExpectedId, customerAddressRequest);

        // then
        verify(customerServiceMock, times(1))
                .updateAddressForCustomerId(anyExpectedId, anyExpectedAddress);
        verifyNoMoreInteractions(customerServiceMock);
        assertEquals(returnedCustomer, actual);
    }

    @Test
    void onCreatingCustomer_shouldInteractWithCollaborators() {
        // given
        CustomerCreateRequest anyCustomerCreateRequest = mock(CustomerCreateRequest.class);
        CustomerDto expectedCreatedCustomerMock = mock(CustomerDto.class);
        when(customerServiceMock.create(anyCustomerCreateRequest)).thenReturn(expectedCreatedCustomerMock);

        // when
        final CustomerDto actualCustomer = sut.create(anyCustomerCreateRequest);

        // then
        verify(customerServiceMock, times(1))
                .create(anyCustomerCreateRequest);
        verifyNoMoreInteractions(customerServiceMock);
        assertEquals(expectedCreatedCustomerMock, actualCustomer);
    }

    @Test
    void onAssociatingDeviceToCustomer_shouldInteractWithCollaborators() {
        // given
        Long anyId = 2L;
        String anyUuid = "anyUuid";
        CustomerDto expectedCreatedCustomerMock = mock(CustomerDto.class);
        when(customerServiceMock.associateDeviceToCustomer(anyId, anyUuid)).thenReturn(expectedCreatedCustomerMock);

        // when
        final CustomerDto actualCustomerWithAssociatedDevice = sut.associateDeviceToCustomer(anyId, anyUuid);

        // then
        verify(customerServiceMock, times(1))
                .associateDeviceToCustomer(anyId, anyUuid);
        verifyNoMoreInteractions(customerServiceMock);
        assertEquals(expectedCreatedCustomerMock, actualCustomerWithAssociatedDevice);
    }

    @Test
    void onUpdatingAddress_shouldInteractWithCollaborators() {
        // given
        String anyAddress = "anyAddress";
        Long anyId = 1L;
        CustomerAddressRequest customerAddressRequest = mock(CustomerAddressRequest.class);
        CustomerDto expectedCreatedCustomerMock = mock(CustomerDto.class);
        when(customerAddressRequest.getAddress()).thenReturn(anyAddress);
        when(customerServiceMock.updateAddressForCustomerId(anyId, anyAddress)).thenReturn(expectedCreatedCustomerMock);

        // when
        final CustomerDto actualCustomerWithAssociatedDevice = sut.updateAddress(anyId, customerAddressRequest);

        // then
        verify(customerServiceMock, times(1))
                .updateAddressForCustomerId(anyId, anyAddress);
        verifyNoMoreInteractions(customerServiceMock);
        assertEquals(expectedCreatedCustomerMock, actualCustomerWithAssociatedDevice);
    }
}