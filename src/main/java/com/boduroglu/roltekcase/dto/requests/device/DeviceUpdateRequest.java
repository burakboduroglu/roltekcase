package com.boduroglu.roltekcase.dto.requests.device;

import lombok.Data;

import java.util.Optional;

@Data
public class DeviceUpdateRequest {
    private Optional<String> brand = Optional.empty();
    private Optional<String> model = Optional.empty();
    private Optional<Long> serialNumber = Optional.empty();
}
