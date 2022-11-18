package com.pg.b2c.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_condition")
public class PaymentCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AvailableInAccount availableInAccount;

    private int maximumInstallmentsNumber;
    private int installmentPeriod;
    private int firstInstallmentAfter;


    @Enumerated(EnumType.STRING)
    private PaymentModality paymentModality;

    @Column(name = "bank_tax_amount")
    private BigDecimal bankTaxAmount;


    @Column(name = "credit_provider_tax_percent")
    private BigDecimal creditProviderTaxPercent;


    @OneToOne
    @JoinColumn(name = "account_plan_id")
    private AccountPlan accountPlan;


    private String observation;





}
