package com.boduroglu.roltekcase.dto.requests;

import lombok.Data;

@Data
public class DeviceCreateRequest {
    private Long serialNumber;
    private String brand;
    private String model;
}
