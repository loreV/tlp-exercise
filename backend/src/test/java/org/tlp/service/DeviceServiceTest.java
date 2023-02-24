package org.tlp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tlp.dto.DeviceDto;
import org.tlp.dto.DeviceStatusDto;
import org.tlp.entity.DeviceEntity;
import org.tlp.entity.DeviceStatusEntity;
import org.tlp.exception.NotFoundItemException;
import org.tlp.internal.Device;
import org.tlp.internal.DeviceStatus;
import org.tlp.mapper.dto.DeviceDtoMapper;
import org.tlp.mapper.dto.DeviceStatusDtoMapper;
import org.tlp.mapper.entity.DeviceEntityMapper;
import org.tlp.mapper.entity.DeviceEntityStatusMapper;
import org.tlp.repository.DeviceRepository;
import org.tlp.resource.request.DeviceUpdateRequest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {
    @Mock
    private DeviceRepository deviceRepositoryMock;
    @Mock
    private DeviceDtoMapper deviceDtoMapperMock;
    @Mock
    private DeviceEntityMapper deviceEntityMapperMock;
    @Mock
    private DeviceEntityStatusMapper deviceStatusEntityMapperMock;
    @Mock
    private DeviceStatusDtoMapper deviceStatusDtoMapperMock;

    private DeviceService sut;

    @BeforeEach
    public void init() {
        sut = new DeviceService(deviceRepositoryMock, deviceDtoMapperMock, deviceEntityMapperMock, deviceStatusEntityMapperMock, deviceStatusDtoMapperMock);
    }

    @Test
    void onGettingAll_shouldReturnAllAvailableValues() {
        // given
        DeviceDto deviceDtoMock = mock(DeviceDto.class);
        Device deviceMock = mock(Device.class);
        DeviceEntity deviceEntityMock = mock(DeviceEntity.class);
        List<DeviceEntity> returnedList = List.of(deviceEntityMock);
        when(deviceRepositoryMock.findAll()).thenReturn(returnedList);
        when(deviceDtoMapperMock.mapFrom(deviceMock)).thenReturn(deviceDtoMock);
        when(deviceEntityMapperMock.mapTo(deviceEntityMock)).thenReturn(deviceMock);

        // when
        List<DeviceDto> actual = sut.getAll();

        // then
        verify(deviceRepositoryMock, times(1))
                .findAll();
        verify(deviceDtoMapperMock, times(1))
                .mapFrom(deviceMock);
        verify(deviceEntityMapperMock, times(1))
                .mapTo(deviceEntityMock);
        assertThat(actual, hasSize(1));
        assertThat(actual, hasItem(deviceDtoMock));
    }

    @Test
    void onValueUpdate_shouldSetValuesAndCallCollaborators() {
        // given
        DeviceEntity expectedDeviceEntity = mock(DeviceEntity.class);
        Device mappedDevice = mock(Device.class);
        DeviceDto deviceDtoToReturn = mock(DeviceDto.class);
        DeviceUpdateRequest deviceUpdateRequestMock = mock(DeviceUpdateRequest.class);

        DeviceStatusDto deviceStatusDtoFromUpdateRequest = DeviceStatusDto.ACTIVE;
        DeviceStatusEntity deviceStatusEntity = DeviceStatusEntity.ACTIVE;
        DeviceStatus deviceMappedStatus = DeviceStatus.ACTIVE;

        String anyUuid = "anyUuid";
        String anyColor = "anyColor";

        when(deviceRepositoryMock.findByUuid(anyUuid)).thenReturn(Optional.of(expectedDeviceEntity));
        when(deviceUpdateRequestMock.getColor()).thenReturn(anyColor);
        when(deviceUpdateRequestMock.getDeviceStatus()).thenReturn(deviceStatusDtoFromUpdateRequest);
        when(deviceStatusDtoMapperMock.mapTo(deviceStatusDtoFromUpdateRequest)).thenReturn(deviceMappedStatus);
        when(deviceStatusEntityMapperMock.mapTo(deviceMappedStatus)).thenReturn(deviceStatusEntity);
        when(deviceRepositoryMock.save(expectedDeviceEntity)).thenReturn(expectedDeviceEntity);
        when(deviceEntityMapperMock.mapTo(expectedDeviceEntity)).thenReturn(mappedDevice);
        when(deviceDtoMapperMock.mapFrom(mappedDevice)).thenReturn(deviceDtoToReturn);

        // when
        final DeviceDto updatedValue = sut.update(anyUuid, deviceUpdateRequestMock);

        // then
        verify(expectedDeviceEntity, times(1))
                .setColor(anyColor);
        verify(expectedDeviceEntity, times(1))
                .setStatus(deviceStatusEntity);
        verify(deviceRepositoryMock, times(1))
                .save(expectedDeviceEntity);
        verify(deviceDtoMapperMock, times(1))
                .mapFrom(mappedDevice);
        verify(deviceEntityMapperMock, times(1))
                .mapTo(expectedDeviceEntity);
        verify(deviceRepositoryMock, times(1))
                .findByUuid(anyUuid);
        verify(deviceStatusEntityMapperMock, times(1))
                .mapTo(deviceMappedStatus);
        verify(deviceStatusDtoMapperMock, times(1))
                .mapTo(deviceStatusDtoFromUpdateRequest);

        verifyNoMoreInteractions(expectedDeviceEntity);
        verifyNoMoreInteractions(deviceDtoMapperMock);
        verifyNoMoreInteractions(deviceStatusDtoMapperMock);
        verifyNoMoreInteractions(deviceRepositoryMock);
        verifyNoMoreInteractions(deviceEntityMapperMock);

        assertEquals(deviceDtoToReturn, updatedValue);
    }

    @Test
    void onNotExistingValueUpdate_shouldThrowNotFoundException() {
        // given
        String anyUuid = "anyUuid";
        DeviceUpdateRequest deviceUpdateRequestMock = mock(DeviceUpdateRequest.class);

        when(deviceRepositoryMock.findByUuid(anyUuid)).thenReturn(Optional.empty());

        // when-then
        assertThrows(NotFoundItemException.class, ()-> sut.update(anyUuid, deviceUpdateRequestMock));

        verifyNoMoreInteractions(deviceRepositoryMock);
        verifyNoInteractions(deviceDtoMapperMock, deviceEntityMapperMock,
                deviceStatusEntityMapperMock, deviceStatusDtoMapperMock);
    }

    @Test
    void onDeletingValue_shouldEnsureCallToCollaborator() {
        // given
        String anyUuid = "anyUuid";

        // when
        sut.delete(anyUuid);

        // then
        verify(deviceRepositoryMock, times(1)).deleteDevice(anyUuid);
    }

    @Test
    void whenCallingDeviceExistByUuid_shouldReturnTrue() {
        // given
        String anyUuid = "anyUuid";
        when(deviceRepositoryMock.existsByUuid(anyUuid)).thenReturn(true);

        // when
        final boolean actual = sut.doesDeviceExist(anyUuid);

        // then
        assertTrue(actual);
    }

    @Test
    void whenCallingDeviceExistByUuid_shouldReturnFalse() {
        // given
        String anyUuid = "anyUuid";
        when(deviceRepositoryMock.existsByUuid(anyUuid)).thenReturn(false);

        // when
        final boolean actual = sut.doesDeviceExist(anyUuid);

        // then
        assertFalse(actual);
    }

    @Test
    void whenCallingCreate_shouldReturnPersistedValueCallingCollaborators() {
        // given
        DeviceDto deviceCreateRequestMock = mock(DeviceDto.class);
        Device mappedDeviceMock = mock(Device.class);
        DeviceEntity deviceEntityMock = mock(DeviceEntity.class);
        when(deviceDtoMapperMock.mapTo(deviceCreateRequestMock)).thenReturn(mappedDeviceMock);
        when(deviceEntityMapperMock.mapFrom(mappedDeviceMock)).thenReturn(deviceEntityMock);
        when(deviceEntityMapperMock.mapTo(deviceEntityMock)).thenReturn(mappedDeviceMock);
        when(deviceDtoMapperMock.mapFrom(mappedDeviceMock)).thenReturn(deviceCreateRequestMock);
        when(deviceRepositoryMock.save(deviceEntityMock)).thenReturn(deviceEntityMock);

        // when
        final DeviceDto actual = sut.create(deviceCreateRequestMock);

        // then
        verify(deviceDtoMapperMock, times(1)).mapTo(deviceCreateRequestMock);
        verify(deviceDtoMapperMock, times(1)).mapFrom(mappedDeviceMock);
        verify(deviceEntityMapperMock, times(1)).mapFrom(mappedDeviceMock);
        verify(deviceEntityMapperMock, times(1)).mapTo(deviceEntityMock);
        verify(deviceRepositoryMock, times(1)).save(deviceEntityMock);

        assertEquals(deviceCreateRequestMock, actual);
    }

}