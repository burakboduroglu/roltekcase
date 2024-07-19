package com.boduroglu.roltekcase.services;

import com.boduroglu.roltekcase.dto.requests.device.DeviceCreateRequest;
import com.boduroglu.roltekcase.dto.requests.device.DeviceUpdateRequest;
import com.boduroglu.roltekcase.models.Device;
import com.boduroglu.roltekcase.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    // Create
    public Device save(DeviceCreateRequest newDeviceRequest) {
        Device toSave = new Device();

        toSave.setBrand(newDeviceRequest.getBrand());
        toSave.setModel(newDeviceRequest.getModel());
        toSave.setSerialNumber(newDeviceRequest.getSerialNumber());

        return deviceRepository.save(toSave);
    }

    // Find by device id
    public Device findById(Long id) {
        return deviceRepository.findById(id).orElse(null);
    }

    // Find by serial number
    public Device findBySerialNumber(Long serialNumber) {
        return deviceRepository.findBySerialNumber(serialNumber);
    }

    // Get all device
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    // Delete a device by device id
    public void deleteId(Long id) {
        deviceRepository.deleteById(id);
    }

    // Delete a device by serial number
    public void deleteBySerialNumber(Long serialNumber) {
       Device device = deviceRepository.findBySerialNumber(serialNumber);
       deviceRepository.deleteById(device.getId());
    }

    // Update a device by id
    public void updateDeviceById(Long id, DeviceUpdateRequest newDeviceRequest) {
        Device device = findById(id);
        newDeviceRequest.getBrand().ifPresent(device::setBrand);
        newDeviceRequest.getModel().ifPresent(device::setModel);
        newDeviceRequest.getSerialNumber().ifPresent(device::setSerialNumber);
        deviceRepository.save(device);
    }
}
