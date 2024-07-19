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

    public List<Warranty> getAllWarranties() {
        return warrantyRepository.findAll();
    }

    public Warranty save(WarrantyCreateRequest warranty) {
        Warranty newWarranty = new Warranty();
        if(warranty.getWarrantyStatus() == 0) newWarranty.setWarrantyStatus(2);
        else newWarranty.setWarrantyStatus(warranty.getWarrantyStatus());
        newWarranty.setDevice(warranty.getDevice());
        newWarranty.setPurchaseDate(warranty.getPurchaseDate());

        return warrantyRepository.save(newWarranty);
    }

    public Warranty getWarrantyById(Long id) {
        return warrantyRepository.findWarrantyById(id);
    }

    public String getWarrantyStatusById(Long id) {
        Warranty warranty = getWarrantyById(id);
        return calculateWarrantyStatusByWarranty(warranty);

    }

    public String calculateWarrantyStatusByWarranty(Warranty warranty) {
       long remainTime = ChronoUnit.YEARS.between(warranty.getPurchaseDate(), LocalDate.now());
       if(remainTime <= warranty.getWarrantyStatus()){
        return "Warranty: " + remainTime + " years";
       }
       return  "Warranty over: " + remainTime + " years";
    }
}
