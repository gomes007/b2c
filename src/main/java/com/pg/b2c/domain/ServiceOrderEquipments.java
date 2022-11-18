package com.pg.b2c.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service_order_equipment")
public class ServiceOrderEquipments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String brand;
    private String model;

    private String seriesNumber;

    private String conditions;
    private String flaws;
    private String fittings;
    private String solution;
    private String technicalReport;
    private String warrantyTerms;

}
