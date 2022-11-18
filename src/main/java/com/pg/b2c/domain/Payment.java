package com.pg.b2c.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Boolean lumpSumPayment;
    private Boolean installmentPayment;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date billDueDate;

    private BigDecimal installmentValue;


    @OneToOne
    @JoinColumn(name = "payment_condition_id")
    private PaymentCondition paymentCondition;


}
