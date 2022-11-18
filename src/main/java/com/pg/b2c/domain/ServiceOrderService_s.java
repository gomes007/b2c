package com.pg.b2c.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service_order_service_")
public class ServiceOrderService_s {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "service_id")
    private Service_s service_s_;

    private String details;
    private int quantity;
    private BigDecimal discountAmount;
    private BigDecimal discountPercent;
    private BigDecimal totalValueService_s;


}
