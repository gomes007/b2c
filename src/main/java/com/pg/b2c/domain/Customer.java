package com.pg.b2c.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer extends Person{

    @Embedded
    private PersonalInformation personalInformation;

    private BigDecimal creditLimit;

    @JoinColumn(name = "customer_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;

}
