package org.tlp.integration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.tlp.dto.DeviceDto;
import org.tlp.dto.DeviceStatusDto;
import org.tlp.exception.NotFoundItemException;
import org.tlp.resource.DeviceResource;
import org.tlp.resource.request.DeviceUpdateRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DeviceIntegrationTest {

    @Autowired
    private DeviceResource sut;

    @Test
    void shouldFindExistingDevice() {
        // given
        DeviceDto deviceDto = new DeviceDto("UUIIDD", "myColor", DeviceStatusDto.INACTIVE);
        DeviceDto createdDevice = sut.create(deviceDto);

        // when
        ResponseEntity<String> headCheck = sut.checkExistence(createdDevice.getUuid());

        // then
        assertEquals(HttpStatus.FOUND, headCheck.getStatusCode());
    }

    @Test
    void shouldUpdateDeviceColorAndStatus () {
        // given
        String uuid = "UUIIDD";
        DeviceDto deviceDto = new DeviceDto(uuid, "myColor", DeviceStatusDto.INACTIVE);
        String expectedColor = "blue";
        DeviceUpdateRequest deviceUpdateRequest = new DeviceUpdateRequest(DeviceStatusDto.ACTIVE, expectedColor);
        sut.create(deviceDto);

        // when
        DeviceDto updatedDevice = sut.update(uuid, deviceUpdateRequest);

        // then
        assertEquals(expectedColor, updatedDevice.getColor());
        assertEquals(DeviceStatusDto.ACTIVE, updatedDevice.getStatus());
    }

    @Test
    void shouldDeleteDevice () {
        // given
        String uuid = "UUIIDD";
        DeviceDto deviceDto = new DeviceDto(uuid, "myColor", DeviceStatusDto.INACTIVE);
        DeviceDto createdDevice = sut.create(deviceDto);

        // when
        sut.delete(uuid);

        // then
        ResponseEntity<String> headCheck = sut.checkExistence(createdDevice.getUuid());
        assertEquals(HttpStatus.NOT_FOUND, headCheck.getStatusCode());
    }

    @Test
    void shouldThrowExceptionOnUpdatingNotExistingDevice () {
        // given
        DeviceUpdateRequest deviceUpdateRequest = new DeviceUpdateRequest(DeviceStatusDto.ACTIVE, "anyColor");

        // when-then
        assertThrows(NotFoundItemException.class, ()-> sut.update("NOT_EXISTING_UUID", deviceUpdateRequest));
    }
}
