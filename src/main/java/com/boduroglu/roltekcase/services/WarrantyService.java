package com.boduroglu.roltekcase.services;

import com.boduroglu.roltekcase.dto.requests.warranty.WarrantyCreateRequest;
import com.boduroglu.roltekcase.models.Warranty;
import com.boduroglu.roltekcase.repositories.WarrantyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class WarrantyService {
    private final WarrantyRepository warrantyRepository;

    public WarrantyService(WarrantyRepository warrantyRepository) {
        this.warrantyRepository = warrantyRepository;
    }

    // Get all warranties
    public List<Warranty> getAllWarranties() {
        return warrantyRepository.findAll();
    }

    // Create warranty for device
    public Warranty save(WarrantyCreateRequest warranty) {
        Warranty newWarranty = new Warranty();
        newWarranty.setWarrantyStatus(warranty.getWarrantyStatus());
        newWarranty.setDevice(warranty.getDevice());
        newWarranty.setPurchaseDate(warranty.getPurchaseDate());

        return warrantyRepository.save(newWarranty);
    }

    // Get warranty by id
    public Warranty getWarrantyById(Long id) {
        return warrantyRepository.findWarrantyById(id);
    }

    // Get warranty status
    public String getWarrantyStatusById(Long id) {
        Warranty warranty = getWarrantyById(id);
        return calculateWarrantyStatusByWarranty(warranty);

    }

    // Calculate remaining time to warranty
    public String calculateWarrantyStatusByWarranty(Warranty warranty) {
       long remainTime = ChronoUnit.YEARS.between(warranty.getPurchaseDate(), LocalDate.now());
       if(remainTime < 2){
        return "Warranty continues: " + remainTime + " years";
       }
       warranty.setWarrantyStatus(false);
       return  "Warranty over: " + remainTime + " years";
    }
}
