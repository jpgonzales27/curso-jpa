package com.jpa.gadget_plus.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "bill")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillEntity {

    @Id
    @Column(nullable = false,length = 64)
    private String id;

    private BigDecimal totalAmount;

    @Column(name = "client_rfc" , length = 14, nullable = false)
    private String rfc;


}
