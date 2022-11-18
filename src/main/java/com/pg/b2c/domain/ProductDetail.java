package com.pg.b2c.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {

    private int weight;
    private int width;
    private int height;
    private int length;
    private String productDescription;

}
