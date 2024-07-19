package com.boduroglu.roltekcase.services;

import com.boduroglu.roltekcase.models.Warranty;
import com.boduroglu.roltekcase.repositories.WarrantyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarrantyService {
    @Autowired
    WarrantyRepository warrantyRepository;
    @Autowired
    DeviceService deviceService;

    public WarrantyService(WarrantyRepository warrantyRepository, DeviceService deviceService) {
        this.warrantyRepository = warrantyRepository;
        this.deviceService = deviceService;
    }

    public Warranty save(Warranty warranty) {
        return warrantyRepository.save(warranty);
    }

    public Warranty getWarrantyByDeviceId(Long id) {
        return warrantyRepository.findWarrantyById(id);
    }
}
