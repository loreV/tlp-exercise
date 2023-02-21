package org.tlp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tlp.dto.CustomerDto;
import org.tlp.entity.CustomerEntity;
import org.tlp.internal.Customer;
import org.tlp.mapper.dto.CustomerDtoMapper;
import org.tlp.mapper.entity.CustomerEntityMapper;
import org.tlp.repository.CustomerRepository;
import org.tlp.repository.DeviceRepository;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private @Mock CustomerRepository customerRepositoryMock;
    private @Mock DeviceRepository deviceRepositoryMock;
    private @Mock CustomerDtoMapper customerDtoMapperMock;
    private @Mock CustomerEntityMapper customerEntityMapper;
    private CustomerService sut;

    @BeforeEach
    public void setUp() {
        sut = new CustomerService(customerRepositoryMock, deviceRepositoryMock, customerDtoMapperMock, customerEntityMapper);
    }

    @Test
    public void onGettingAll_shouldReturnAllAvailableValues() {
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



}