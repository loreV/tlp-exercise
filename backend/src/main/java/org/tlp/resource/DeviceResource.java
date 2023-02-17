package org.tlp.resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/device")
public class DeviceResource {

    @DeleteMapping("/{uuid}")
    public void delete(String uuid){}
}
