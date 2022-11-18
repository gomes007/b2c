package com.pg.b2c.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "serviceOrder")
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceOrderStatus serviceOrderStatus;


    private LocalDateTime startDate;
    private LocalDateTime endDate;


    @OneToOne
    @JoinColumn(name = "cost_center_id")
    private CostCenter costCenter;


    @OneToOne
    @JoinColumn(name = "seller_id")
    private Employee seller;

    @OneToOne
    @JoinColumn(name = "technician_id")
    private Employee technician;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_order_equipment_id")
    private List<ServiceOrderEquipments> serviceOrderEquipments;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_order_product_id")
    private List<ServiceOrderProduct> serviceOrderProducts;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_order_service_id")
    private List<ServiceOrderService_s> serviceOrderService_s;


    private BigDecimal totalProducts;
    private BigDecimal totalService_s;
    private BigDecimal shippingCost;
    private BigDecimal otherCosts;
    private BigDecimal discountAmount;
    private BigDecimal discountPercent;
    private BigDecimal totalCostServiceOrder;

    private Boolean informAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_order_address_id")
    private Address deliveryAddress;

    private Boolean createPaymentConditions;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_order_payment_id")
    private List<Payment> payments;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_order_attachments_id")
    private List<Attachment> attachments;

    @Column(name = "other_information")
    private String otherInformation;


}
