package com.jpa.gadget_plus.repositories;

import com.jpa.gadget_plus.entities.ProductCatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductCatalogRepository extends JpaRepository<ProductCatalogEntity, UUID> {

    //select * from product_catalog where name = ''
    Optional<ProductCatalogEntity> findByName(String name);
    List<ProductCatalogEntity> findByNameLike(String key);
    List<ProductCatalogEntity> findByPriceBetween(BigDecimal min, BigDecimal max);
    @Query("from productCatalog p where p.price between :min and :max")
    List<ProductCatalogEntity> findBetweenByQuery(BigDecimal min, BigDecimal max);
}
