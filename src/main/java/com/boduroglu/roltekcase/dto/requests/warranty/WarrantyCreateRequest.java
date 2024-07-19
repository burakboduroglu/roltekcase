package com.boduroglu.roltekcase.dto.requests.warranty;

import com.boduroglu.roltekcase.models.Device;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WarrantyCreateRequest {
    LocalDate purchaseDate;
    Boolean warrantyStatus;
    Device device;
}
