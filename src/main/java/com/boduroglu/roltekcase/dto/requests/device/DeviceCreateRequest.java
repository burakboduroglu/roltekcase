package com.boduroglu.roltekcase.dto.requests.device;

import lombok.Data;

@Data
public class DeviceCreateRequest {
    private Long serialNumber;
    private String brand;
    private String model;
}
