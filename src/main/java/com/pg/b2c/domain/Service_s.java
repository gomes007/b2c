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
@Table(name = "service")
public class Service_s {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

   private String serviceName;
   private String serviceCode;
   private String description;
   private BigDecimal salesCommission;

   @Embedded
   private Price price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_attachments_id")
   private List<Attachment> attachments;

}
