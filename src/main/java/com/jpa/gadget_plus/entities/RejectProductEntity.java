package com.jpa.gadget_plus.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "reject_products")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(RejectProductId.class)
public class RejectProductEntity {

    @Id
    private String productName;
    @Id
    private String brandName;
    private Integer quantity;
}
