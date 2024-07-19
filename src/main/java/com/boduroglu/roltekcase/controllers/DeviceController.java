package com.boduroglu.roltekcase.controllers;

import com.boduroglu.roltekcase.dto.requests.device.DeviceCreateRequest;
import com.boduroglu.roltekcase.dto.requests.device.DeviceUpdateRequest;
import com.boduroglu.roltekcase.exceptions.DeviceNotFoundException;
import com.boduroglu.roltekcase.models.Device;
import com.boduroglu.roltekcase.services.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    // Get all devices
    @GetMapping
    public  ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.findAll();
        if (devices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(devices);
    }

    // Get a device by device id
    @GetMapping("/id/{id}")
    public ResponseEntity<Device> getDevice(@PathVariable Long id) {
        Device device = deviceService.findById(id);
        if (device == null) {
            throw new DeviceNotFoundException("Device with id " + id + " not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(device);
    }

    // Get a device by serial number
    @GetMapping("/serial-number/{serialNumber}")
    public ResponseEntity<Device> getDeviceBySerialNumber(@PathVariable Long serialNumber) {
        Device device = deviceService.findBySerialNumber(serialNumber);
        if (device == null) {
            throw new DeviceNotFoundException("Device with serial number " + serialNumber + " not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(device);
    }

    // Create a new device
    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody DeviceCreateRequest newDevice) {
        Device device = deviceService.save(newDevice);
        if (device != null)
            return ResponseEntity.status(HttpStatus.OK).body(device);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Delete a device by id
    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteDeviceById(@PathVariable Long id) {
        Device device = deviceService.findById(id);
        if (device == null) {
            throw new DeviceNotFoundException("Device with id " + id + " not found");
        }
        try {
            deviceService.deleteId(id);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Device with id " + id + " successfully deleted");
    }

    // Delete a device by serial number
    @DeleteMapping("/serial-number/{serialNumber}")
    public ResponseEntity<String> deleteDeviceBySerialNumber(@PathVariable Long serialNumber) {
        Device device = deviceService.findBySerialNumber(serialNumber);
        if (device == null) {
            throw new DeviceNotFoundException("Device with id " + serialNumber + " not found");
        }
        try {
            deviceService.deleteBySerialNumber(serialNumber);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Device with id " + serialNumber + " successfully deleted");
    }

    // Update a device by id
    @PatchMapping("/id/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody DeviceUpdateRequest newDevice) {
        Device device = deviceService.findById(id);
        if (device == null) {
            throw new DeviceNotFoundException("Device with id " + id + " not found");
        }
        deviceService.updateDeviceById(id, newDevice);
        return ResponseEntity.status(HttpStatus.OK).body(device);
    }

    // Exception handler
    @ExceptionHandler(DeviceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handleDeviceNotFound() {}
}
