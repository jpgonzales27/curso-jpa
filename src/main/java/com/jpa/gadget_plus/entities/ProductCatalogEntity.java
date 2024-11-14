package com.jpa.gadget_plus.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "products_catalog")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCatalogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_name", length = 64)
    private String name;

    @Column(name = "brand_name", length = 64)
    private String brand;

    private String description;
    private BigDecimal price;

    private LocalDateTime launchingDate;
    private Boolean isDiscount;
    private Short rating;

    @ToString.Exclude
    @OneToOne(mappedBy = "catalog", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ProductEntity product;
}
