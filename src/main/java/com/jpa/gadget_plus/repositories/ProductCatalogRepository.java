package com.jpa.gadget_plus.repositories;

import com.jpa.gadget_plus.entities.ProductCatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCatalogRepository extends JpaRepository<ProductCatalogEntity, UUID> {
}
