package com.boduroglu.roltekcase.repositories;

import com.boduroglu.roltekcase.models.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
    Warranty findWarrantyById(Long id);

}
