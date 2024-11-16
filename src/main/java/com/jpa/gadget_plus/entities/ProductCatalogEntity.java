package com.jpa.gadget_plus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "productCatalog")
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
    @JsonIgnore
    @OneToOne(mappedBy = "catalog", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ProductEntity product;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "product_join_category",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private List<CategoryEntity> categories = new ArrayList<>();

    public void addCategory(CategoryEntity category) {
        this.categories.add(category);
    }
}
