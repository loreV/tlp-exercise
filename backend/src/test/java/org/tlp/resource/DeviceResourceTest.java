package org.tlp.resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.tlp.dto.DeviceDto;
import org.tlp.resource.request.DeviceUpdateRequest;
import org.tlp.service.DeviceService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeviceResourceTest {
    @Mock
    private DeviceService deviceServiceMock;

    private DeviceResource sut;

    @BeforeEach
    public void setUp() {
        sut = new DeviceResource(deviceServiceMock);
    }

    @Test
    void shouldGetAllDevices() {
        // given
        List<DeviceDto> anyExpectedList = Mockito.mock(List.class);
        when(deviceServiceMock.getAll()).thenReturn(anyExpectedList);

        // when
        List<DeviceDto> actual = sut.get();

        // then
        verify(deviceServiceMock, times(1)).getAll();
        verifyNoMoreInteractions(deviceServiceMock);
        assertEquals(anyExpectedList, actual);
    }

    @Test
    void onCreatingDevice_shouldInteractWithCollaborators() {
        // given
        DeviceDto expectedCreatedDeviceMock = mock(DeviceDto.class);
        when(deviceServiceMock.create(expectedCreatedDeviceMock)).thenReturn(expectedCreatedDeviceMock);

        // when
        final DeviceDto actualDevice = sut.create(expectedCreatedDeviceMock);

        // then
        verify(deviceServiceMock, times(1))
                .create(expectedCreatedDeviceMock);
        verifyNoMoreInteractions(deviceServiceMock);
        assertEquals(expectedCreatedDeviceMock, actualDevice);
    }

    @Test
    void onUpdatingDevice_shouldInteractWithCollaborators() {
        // given
        DeviceDto expectedCreatedDeviceMock = mock(DeviceDto.class);
        DeviceUpdateRequest expectedUpdateRequestMock = mock(DeviceUpdateRequest.class);
        String anyUuid = "anyUuid";
        when(deviceServiceMock.update(anyUuid, expectedUpdateRequestMock)).thenReturn(expectedCreatedDeviceMock);

        // when
        final DeviceDto actualDevice = sut.update(anyUuid, expectedUpdateRequestMock);

        // then
        verify(deviceServiceMock, times(1))
                .update(anyUuid, expectedUpdateRequestMock);
        verifyNoMoreInteractions(deviceServiceMock);
        assertEquals(expectedCreatedDeviceMock, actualDevice);
    }

    @Test
    void onCheckingExistenceAndDeviceExist_shouldReturnStatusWithCodeFound() {
        // given
        String anyUuid = "anyUuid";
        when(deviceServiceMock.doesDeviceExist(anyUuid)).thenReturn(true);

        // when
        ResponseEntity<String> stringResponseEntity = sut.checkExistence(anyUuid);

        // then
        verify(deviceServiceMock, times(1))
                .doesDeviceExist(anyUuid);
        verifyNoMoreInteractions(deviceServiceMock);
        assertEquals(HttpStatusCode.valueOf(302), stringResponseEntity.getStatusCode());
    }

    @Test
    void onCheckingExistenceAndDeviceNotExist_shouldReturnStatusWithCodeNotFound() {
        // given
        String anyUuid = "anyUuid";
        when(deviceServiceMock.doesDeviceExist(anyUuid)).thenReturn(false);

        // when
        ResponseEntity<String> stringResponseEntity = sut.checkExistence(anyUuid);

        // then
        verify(deviceServiceMock, times(1))
                .doesDeviceExist(anyUuid);
        verifyNoMoreInteractions(deviceServiceMock);
        assertEquals(HttpStatusCode.valueOf(404), stringResponseEntity.getStatusCode());

    }

}