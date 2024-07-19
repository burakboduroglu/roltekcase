package com.boduroglu.roltekcase.repositories;

import com.boduroglu.roltekcase.models.Device;
import com.boduroglu.roltekcase.models.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findBySerialNumber(Long id);
}
