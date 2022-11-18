package com.pg.b2c.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String fantasyName;
    private String cnpj;
    private String email;
    private String phone;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provider_address_id")
    private Address providerAddress;



//    @ManyToMany(mappedBy = "providers", fetch = FetchType.LAZY)
////    @JsonBackReference
//    private List<Product> products;


}
