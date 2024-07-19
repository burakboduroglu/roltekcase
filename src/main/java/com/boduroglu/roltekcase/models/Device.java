package com.boduroglu.roltekcase.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long serialNumber;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;
}
