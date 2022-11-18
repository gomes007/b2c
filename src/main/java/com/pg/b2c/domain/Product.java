package com.pg.b2c.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String barCode;

    @Embedded
    private ProductDetail productDetail;

    @Embedded
    private Price price;

    @OneToOne
    @JoinColumn(name = "product_cluster_id")
    private ProductCluster productCluster;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_inventory_id")
    private ProductInventory productInventory;


//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "PRODUCT_PROVIDER_TABLE",
//            joinColumns = {
//                @JoinColumn(name="product_id", referencedColumnName = "id")},
//            inverseJoinColumns = {
//                @JoinColumn(name="provider_id", referencedColumnName = "id")})
////    @JsonManagedReference
//    private List<Provider> providers;


    @OneToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_attachments_id")
    private List<Attachment> attachments;

}
