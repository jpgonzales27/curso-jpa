package com.jpa.gadget_plus.repositories;

import com.jpa.gadget_plus.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
}
