package org.tlp.resource;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tlp.dto.DeviceDto;
import org.tlp.resource.request.DeviceUpdateRequest;
import org.tlp.service.DeviceService;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceResource {

    private final DeviceService deviceService;

    public DeviceResource(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public List<DeviceDto> get() {
        return deviceService.getAll();
    }

    @PostMapping
    public DeviceDto create(@RequestBody DeviceDto deviceCreateRequest) {
        return deviceService.create(deviceCreateRequest);
    }

    @PutMapping("/{uuid}")
    public DeviceDto update(@PathVariable String uuid, @RequestBody DeviceUpdateRequest deviceUpdateRequest) {
        return deviceService.update(uuid, deviceUpdateRequest);
    }

    @RequestMapping(value = "/{uuid}", method = {RequestMethod.HEAD})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Device not found"),
            @ApiResponse(responseCode = "302", description = "Device found")})
    public ResponseEntity<String> checkExistence(@PathVariable String uuid){
        boolean deviceExisting = deviceService.isDeviceExisting(uuid);
        HttpStatusCode responseStatusBuilder = getResponseStatusBuilder(deviceExisting);
        return ResponseEntity.status(responseStatusBuilder).build();
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable String uuid) {
        deviceService.delete(uuid);
    }

    private HttpStatusCode getResponseStatusBuilder(boolean isDeviceExisting) {
        if(isDeviceExisting){
            return HttpStatus.FOUND;
        }
        return HttpStatus.NOT_FOUND;
    }
}
