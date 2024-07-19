package com.boduroglu.roltekcase.services;

import com.boduroglu.roltekcase.dto.requests.warranty.WarrantyCreateRequest;
import com.boduroglu.roltekcase.models.Device;
import com.boduroglu.roltekcase.models.Warranty;
import com.boduroglu.roltekcase.repositories.WarrantyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarrantyService {
    private final WarrantyRepository warrantyRepository;
    private final DeviceService deviceService;

    public WarrantyService(WarrantyRepository warrantyRepository, DeviceService deviceService) {
        this.warrantyRepository = warrantyRepository;
        this.deviceService = deviceService;
    }

    public List<Warranty> getAllWarranties() {
        return warrantyRepository.findAll();
    }

    public Warranty save(WarrantyCreateRequest warranty) {
        Device deviceId = deviceService.findById(warranty.getDevice().getId());
        if (deviceId != null ) {
            return null;
        }
        Warranty newWarranty = new Warranty();
        newWarranty.setWarrantyStatus(warranty.getWarrantyStatus());
        newWarranty.setDevice(warranty.getDevice());
        newWarranty.setPurchaseDate(warranty.getPurchaseDate());

        return warrantyRepository.save(newWarranty);
    }

    public Warranty getWarrantyById(Long id) {
        return warrantyRepository.findWarrantyById(id);
    }

    public String getWarrantyStatusById(Long id) {
        Warranty warranty = getWarrantyById(id);
        return warranty.getWarrantyStatus();
    }
}
