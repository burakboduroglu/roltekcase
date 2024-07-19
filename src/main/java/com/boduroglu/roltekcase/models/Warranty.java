package com.boduroglu.roltekcase.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "warranty")
public class Warranty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    LocalDate purchaseDate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    String warrantyStatus;

    @ManyToOne
    @JoinColumn(name = "device", nullable = false, unique = true)
    Device device;
}
