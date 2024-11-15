package com.jpa.gadget_plus.service;

import com.jpa.gadget_plus.entities.ProductCatalogEntity;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ProductCatalogService {

    ProductCatalogEntity findById(UUID id);
    ProductCatalogEntity findByName(String name);
    List<ProductCatalogEntity> findNameLike(String key);
    List<ProductCatalogEntity> findNameBetween(BigDecimal min, BigDecimal max);
    List<ProductCatalogEntity> findByCategoryId(Long id);
    //List<ProductCatalogEntity> findByLaunchingDate(LocalDate date, DateEval key);
    List<ProductCatalogEntity> findByBrandAndRating(String brand, Short rating);
    //List<ReportProduct> makeReport();

    Page<ProductCatalogEntity> findAll(String field, Boolean desc, Integer page);
    Page<ProductCatalogEntity> findAllByBrand(String brand);

    Integer countByBrand(String brand);
}
