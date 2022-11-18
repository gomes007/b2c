package com.pg.b2c.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    private BigDecimal costValue;
    private BigDecimal additionalCosts;
    private BigDecimal finalCosts;
    private BigDecimal marginProfit;
    private BigDecimal salePrice;

}
