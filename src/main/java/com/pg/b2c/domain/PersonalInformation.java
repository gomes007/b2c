package com.pg.b2c.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInformation {

    private String phone;
    private String email;
    private String photo;

}
