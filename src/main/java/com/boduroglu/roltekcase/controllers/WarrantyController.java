package com.boduroglu.roltekcase.controllers;

import com.boduroglu.roltekcase.dto.requests.warranty.WarrantyCreateRequest;
import com.boduroglu.roltekcase.models.Warranty;
import com.boduroglu.roltekcase.services.WarrantyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warranty")
public class WarrantyController {
    private final WarrantyService warrantyService;

    public WarrantyController(WarrantyService warrantyService) {
        this.warrantyService = warrantyService;
    }

    // Get all warranty
    @GetMapping
    public ResponseEntity<List<Warranty>> getWarranty() {
        List<Warranty> warranties = warrantyService.getAllWarranties();
        if (warranties.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(warranties);
    }

    @PostMapping
    public ResponseEntity<Warranty> createWarranty(@RequestBody WarrantyCreateRequest warranty) {
        Warranty newWarranty = warrantyService.save(warranty);
        if (newWarranty == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newWarranty);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Warranty> getWarrantyById(@PathVariable Long id) {
        String status = warrantyService.getWarrantyStatusById(id);
        if (status == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(warrantyService.getWarrantyById(id));
    }
}
