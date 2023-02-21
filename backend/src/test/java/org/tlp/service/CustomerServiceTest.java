package org.tlp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tlp.dto.CustomerDto;
import org.tlp.entity.CustomerEntity;
import org.tlp.entity.DeviceEntity;
import org.tlp.exception.ForbiddenException;
import org.tlp.exception.NotFoundItemException;
import org.tlp.internal.Customer;
import org.tlp.mapper.dto.CustomerDtoMapper;
import org.tlp.mapper.entity.CustomerEntityMapper;
import org.tlp.repository.CustomerRepository;
import org.tlp.repository.DeviceRepository;
import org.tlp.resource.request.CustomerCreateRequest;
import org.tlp.service.provider.CustomerEntityProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private @Mock CustomerRepository customerRepositoryMock;
    private @Mock DeviceRepository deviceRepositoryMock;
    private @Mock CustomerDtoMapper customerDtoMapperMock;
    private @Mock CustomerEntityMapper customerEntityMapper;
    private @Mock CustomerEntityProvider customerEntityProvider;
    private CustomerService sut;

    @BeforeEach
    public void setUp() {
        sut = new CustomerService(customerRepositoryMock, deviceRepositoryMock, customerDtoMapperMock, customerEntityMapper, customerEntityProvider);
    }

    @Test
    void onGettingAll_shouldReturnAllAvailableValues() {
        // given
        CustomerDto customerDtoMock = mock(CustomerDto.class);
        Customer customerMock = mock(Customer.class);
        CustomerEntity customerEntityMock = mock(CustomerEntity.class);
        List<CustomerEntity> returnedList = List.of(customerEntityMock);
        when(customerRepositoryMock.findAll()).thenReturn(returnedList);
        when(customerEntityMapper.mapTo(customerEntityMock)).thenReturn(customerMock);
        when(customerDtoMapperMock.mapTo(customerMock)).thenReturn(customerDtoMock);

        // when
        List<CustomerDto> actual = sut.getAll();

        // then
        verify(customerRepositoryMock, times(1))
                .findAll();
        verify(customerEntityMapper, times(1))
                .mapTo(customerEntityMock);
        verify(customerDtoMapperMock, times(1))
                .mapTo(customerMock);
        assertThat(actual, hasSize(1));
        assertThat(actual, hasItem(customerDtoMock));
    }

    @Test
    void shouldUpdateAddress() {
        // given
        Long anyId = 1L;
        String expectedAddress = "myAddress";
        Customer customerMock = mock(Customer.class);
        CustomerDto expectedDtoMock = mock(CustomerDto.class);
        CustomerEntity customerEntityMock = mock(CustomerEntity.class);
        when(customerRepositoryMock.findById(anyId)).thenReturn(Optional.of(customerEntityMock));
        when(customerRepositoryMock.save(customerEntityMock)).thenReturn(customerEntityMock);
        when(customerEntityMapper.mapTo(customerEntityMock)).thenReturn(customerMock);
        when(customerDtoMapperMock.mapTo(customerMock)).thenReturn(expectedDtoMock);

        // when
        CustomerDto actual = sut.updateAddressForCustomerId(anyId, expectedAddress);

        // then
        verify(customerEntityMock, times(1))
                .setAddress(expectedAddress);
        verify(customerRepositoryMock, times(1))
                .save(customerEntityMock);
        verify(customerRepositoryMock, times(1))
                .findById(anyId);
        verify(customerEntityMapper, times(1))
                .mapTo(customerEntityMock);
        verify(customerDtoMapperMock, times(1))
                .mapTo(customerMock);

        assertEquals(expectedDtoMock, actual);
    }

    @Test
    void whenUpdatingAddressForNotExistingCustomer_shouldThrowException() {
        // given
        Long anyId = 1L;
        String anyAddress = "myAddress";
        when(customerRepositoryMock.findById(anyId)).thenReturn(Optional.empty());

        // when-then
        assertThrows(NotFoundItemException.class, ()-> sut.updateAddressForCustomerId(anyId, anyAddress));
    }

    @Test
    void shouldCreateCustomer() {
        // given
        String expectedAddress = "myAddress";
        String myFirstName = "myAddress";
        String myLastName = "myLastName";
        String myFiscalCode = "myFiscalCode";

        CustomerCreateRequest customerCreateRequestMock = mock(CustomerCreateRequest.class);
        when(customerCreateRequestMock.getAddress()).thenReturn(expectedAddress);
        when(customerCreateRequestMock.getFirstName()).thenReturn(myFirstName);
        when(customerCreateRequestMock.getLastName()).thenReturn(myLastName);
        when(customerCreateRequestMock.getFiscalCode()).thenReturn(myFiscalCode);

        CustomerEntity customerEntityMock = mock(CustomerEntity.class);
        when(customerEntityProvider.provideCustomerEntity(myFirstName, myLastName, myFiscalCode, expectedAddress, emptyList()))
                .thenReturn(customerEntityMock);
        when(customerRepositoryMock.save(customerEntityMock)).thenReturn(customerEntityMock);


        Customer customerMock = mock(Customer.class);
        CustomerDto expectedDtoMock = mock(CustomerDto.class);
        when(customerEntityMapper.mapTo(customerEntityMock)).thenReturn(customerMock);
        when(customerDtoMapperMock.mapTo(customerMock)).thenReturn(expectedDtoMock);

        // when
        CustomerDto actual = sut.create(customerCreateRequestMock);

        // then
        verify(customerEntityProvider, times(1))
                .provideCustomerEntity(myFirstName, myLastName, myFiscalCode, expectedAddress, emptyList());
        verify(customerRepositoryMock, times(1))
                .save(customerEntityMock);
        verify(customerEntityMapper, times(1))
                .mapTo(customerEntityMock);
        verify(customerDtoMapperMock, times(1))
                .mapTo(customerMock);

        assertEquals(expectedDtoMock, actual);
    }

    @Test
    void whenCustomerHasLessThanTwoDevices_shouldAssociateDevice() {
        // given
        Long anyId = 1L;
        String deviceUuid = "myUuid";
        List<DeviceEntity> deviceListMock = mock(ArrayList.class);
        CustomerEntity customerEntityMock = mock(CustomerEntity.class);
        DeviceEntity deviceEntityMock = mock(DeviceEntity.class);
        Customer customerMock = mock(Customer.class);
        CustomerDto expectedDtoMock = mock(CustomerDto.class);

        when(customerEntityMapper.mapTo(customerEntityMock)).thenReturn(customerMock);
        when(customerRepositoryMock.findById(anyId)).thenReturn(Optional.of(customerEntityMock));
        when(deviceRepositoryMock.findByUuid(deviceUuid)).thenReturn(Optional.of(deviceEntityMock));
        when(customerEntityMock.getAssociatedDevices()).thenReturn(deviceListMock);
        when(deviceListMock.size()).thenReturn(0);

        when(customerRepositoryMock.save(customerEntityMock)).thenReturn(customerEntityMock);
        when(customerDtoMapperMock.mapTo(customerMock)).thenReturn(expectedDtoMock);

        // when
        final CustomerDto actual = sut.associateDeviceToCustomer(anyId, deviceUuid);

        // then
        verify(customerRepositoryMock, times(1))
                .save(customerEntityMock);
        verify(customerEntityMapper, times(1))
                .mapTo(customerEntityMock);
        verify(customerDtoMapperMock, times(1))
                .mapTo(customerMock);
        verify(customerRepositoryMock, times(1))
                .save(customerEntityMock);
        verify(deviceListMock, times(1))
                .add(deviceEntityMock);

        assertEquals(expectedDtoMock, actual);
    }

    @Test
    void whenCustomerHasAlreadyTwoDevices_shouldThrowForbiddenException() {
        // given
        Long anyId = 1L;
        String deviceUuid = "myUuid";
        List<DeviceEntity> deviceListMock = mock(ArrayList.class);
        CustomerEntity customerEntityMock = mock(CustomerEntity.class);
        DeviceEntity deviceEntityMock = mock(DeviceEntity.class);

        when(customerRepositoryMock.findById(anyId)).thenReturn(Optional.of(customerEntityMock));
        when(deviceRepositoryMock.findByUuid(deviceUuid)).thenReturn(Optional.of(deviceEntityMock));
        when(customerEntityMock.getAssociatedDevices()).thenReturn(deviceListMock);
        when(deviceListMock.size()).thenReturn(2);

        // when-then
        assertThrows(ForbiddenException.class, () -> sut.associateDeviceToCustomer(anyId, deviceUuid));
    }

    @Test
    void whenNoSuchDeviceExist_shouldThrowNotFoundItemException() {
        // given
        Long anyId = 1L;
        String deviceUuid = "myUuid";

        when(deviceRepositoryMock.findByUuid(deviceUuid)).thenReturn(Optional.empty());

        // when-then
        assertThrows(NotFoundItemException.class, () -> sut.associateDeviceToCustomer(anyId, deviceUuid));
    }

    @Test
    void whenNoSuchCustomerExist_shouldThrowNotFoundItemException() {
        // given
        Long anyId = 1L;
        String deviceUuid = "myUuid";

        when(deviceRepositoryMock.findByUuid(deviceUuid)).thenReturn(Optional.of(mock(DeviceEntity.class)));
        when(customerRepositoryMock.findById(anyId)).thenReturn(Optional.empty());

        // when-then
        assertThrows(NotFoundItemException.class, () -> sut.associateDeviceToCustomer(anyId, deviceUuid));
    }


}