package com.jpa.gadget_plus.repositories;

import com.jpa.gadget_plus.entities.ProductCatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductCatalogRepository extends JpaRepository<ProductCatalogEntity, UUID> {

    //select * from product_catalog where name = ''
    Optional<ProductCatalogEntity> findByName(String name);
}
