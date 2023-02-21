package org.tlp.integration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.tlp.dto.CustomerDto;
import org.tlp.dto.DeviceDto;
import org.tlp.dto.DeviceStatusDto;
import org.tlp.exception.ForbiddenException;
import org.tlp.exception.NotFoundItemException;
import org.tlp.resource.CustomerResource;
import org.tlp.resource.DeviceResource;
import org.tlp.resource.request.CustomerAddressRequest;
import org.tlp.resource.request.CustomerCreateRequest;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CustomerIntegrationTest {

    @Autowired
    private DeviceResource deviceResource;
    @Autowired
    private CustomerResource sut;

    @Test
    void shouldFindExistingCustomer() {
        // given
        CustomerCreateRequest createRequest = new CustomerCreateRequest("myName", "myLastName", "myFiscalCode", "myAddress");
        CustomerDto createdCustomer = sut.create(createRequest);

        // when
        List<CustomerDto> findCustomerCall = sut.get();

        // then
        assertThat(findCustomerCall, hasItem(createdCustomer));
    }

    @Test
    void shouldUpdateCustomerAddress() {
        // given
        String expectedAddress = "Via Belvedere, 44/a";
        CustomerCreateRequest createRequest = new CustomerCreateRequest("myName", "myLastName", "myFiscalCode", "myAddress");
        CustomerDto createdCustomer = sut.create(createRequest);
        CustomerDto updateCustomer = sut.updateAddress(createdCustomer.getId(), new CustomerAddressRequest(expectedAddress));

        // when
        List<CustomerDto> findCustomerCall = sut.get();

        // then
        assertThat(findCustomerCall, hasItem(updateCustomer));
        assertTrue(findCustomerCall.stream()
                .filter(updatedItem -> Objects.equals(updatedItem.getId(), createdCustomer.getId()))
                .allMatch(it-> it.getAddress().equals(expectedAddress)));
    }

    @Test
    void whenCustomerDoesNotExistOnUpdateAddress_shouldThrowNotFoundException() {
        // given
        CustomerCreateRequest createRequest = new CustomerCreateRequest("myName", "myLastName", "myFiscalCode", "myAddress");
        String anyAddress = "Any address, 44/a";
        CustomerDto createdCustomer = sut.create(createRequest);
        Long anyNotExistingCustomerId = 12345L;

        // when-then
        assertThrows(NotFoundItemException.class,
                ()-> sut.updateAddress(anyNotExistingCustomerId, new CustomerAddressRequest(anyAddress)));
    }

    @Test
    void shouldAssociateDeviceToCustomer () {
        // given
        CustomerCreateRequest createRequest = new CustomerCreateRequest("myName", "myLastName",
                "myFiscalCode", "myAddress");
        CustomerDto createdCustomer = sut.create(createRequest);

        String uuid = "UUIIDD";
        DeviceDto deviceDto = new DeviceDto(uuid, "myColor", DeviceStatusDto.INACTIVE);
        final DeviceDto createdDevice = deviceResource.create(deviceDto);

        // when
        CustomerDto updatedDevice = sut.associateDeviceToCustomer(createdCustomer.getId(), createdDevice.getUuid());

        // then
        assertThat(updatedDevice.getAssociatedDevices(), hasItem(createdDevice));
    }

    @Test
    void whenCustomerDoesNotExist_shouldThrowException () {
        String uuid = "UUIIDD";
        DeviceDto deviceDto = new DeviceDto(uuid, "myColor", DeviceStatusDto.INACTIVE);
        DeviceDto createdDevice = deviceResource.create(deviceDto);

        // when-then
        Long anyNotExistingUserId = 12345L;
        assertThrows(NotFoundItemException.class, () ->
                sut.associateDeviceToCustomer(anyNotExistingUserId, createdDevice.getUuid()));

    }

    @Test
    void whenDeviceToAssociateDoesNotExist_shouldThrowException () {
        // given
        CustomerCreateRequest createRequest = new CustomerCreateRequest("myName", "myLastName", "myFiscalCode", "myAddress");
        CustomerDto createdCustomer = sut.create(createRequest);

        // when-then
        assertThrows(NotFoundItemException.class, () ->
                sut.associateDeviceToCustomer(createdCustomer.getId(), "ANY_NOT_EXISTING_UUID"));
    }

    @Test
    void whenUserDeviceAssociationExceedsLimit_shouldThrowForbiddenException() {
        CustomerCreateRequest createRequest = new CustomerCreateRequest("myName", "myLastName",
                "myFiscalCode", "myAddress");
        CustomerDto createdCustomer = sut.create(createRequest);

        DeviceDto deviceDto = new DeviceDto("UUIIDD", "myColor", DeviceStatusDto.INACTIVE);
        DeviceDto device2Dto = new DeviceDto("UUIIDD2", "myColor2", DeviceStatusDto.ACTIVE);
        DeviceDto device3Dto = new DeviceDto("UUIIDD3", "myColor3", DeviceStatusDto.LOST);
        final DeviceDto createdDevice = deviceResource.create(deviceDto);
        final DeviceDto createdDevice2 = deviceResource.create(device2Dto);
        final DeviceDto createdDevice3 = deviceResource.create(device3Dto);

        sut.associateDeviceToCustomer(createdCustomer.getId(), createdDevice.getUuid());
        sut.associateDeviceToCustomer(createdCustomer.getId(), createdDevice2.getUuid());

        // when-then
        assertThrows(ForbiddenException.class, () ->
                sut.associateDeviceToCustomer(createdCustomer.getId(), createdDevice3.getUuid()));
    }

}
