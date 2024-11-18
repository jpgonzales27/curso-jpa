package com.jpa.gadget_plus.service;

import com.jpa.gadget_plus.dtos.ReportProduct;
import com.jpa.gadget_plus.entities.ProductCatalogEntity;
import com.jpa.gadget_plus.enums.DateEval;
import com.jpa.gadget_plus.repositories.ProductCatalogRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductCatalogServiceImpl implements ProductCatalogService {

    private final ProductCatalogRepository productCatalogRepository;
    private static final int PAGE_SIZE = 5;
    private static final int PAGE_MIN_SIZE = 2;

    @Override
    public ProductCatalogEntity findById(UUID id) {
        return productCatalogRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductCatalogEntity findByName(String name) {
        return productCatalogRepository.findByName(name).orElseThrow();
    }

    @Override
    public List<ProductCatalogEntity> findNameLike(String key) {
        return productCatalogRepository.findByNameLike(key);
    }

    @Override
    public List<ProductCatalogEntity> findByPriceBetween(BigDecimal min, BigDecimal max) {
        return productCatalogRepository.findByPriceBetween(min, max);
    }

    @Override
    public List<ProductCatalogEntity> findBetweenByQuery(BigDecimal min, BigDecimal max) {
        return productCatalogRepository.findBetweenByQuery(min, max);
    }

    @Override
    public List<ProductCatalogEntity> findByCategoryId(Long id) {
        return productCatalogRepository.getByCategory(id);
    }

    @Override
    public List<ProductCatalogEntity> findByLaunchingDate(LocalDate date, DateEval key) {
        if (key.equals(DateEval.AFTER)) {
            return this.productCatalogRepository.findByLaunchingDateAfter(date);
        } else {
            return this.productCatalogRepository.findByLaunchingDateBefore(date);
        }
    }

    @Override
    public List<ProductCatalogEntity> findByBrandAndRating(String brand, Short rating) {
        return productCatalogRepository.findByBrandAndRatingGreaterThan(brand, rating);
    }

    @Override
    public List<ProductCatalogEntity> findByBrandOrRating(String brand, Short rating) {
        return productCatalogRepository.findByBrandOrRatingGreaterThan(brand, rating);
    }

    @Override
    public List<ReportProduct> makeReport() {
        return productCatalogRepository.findAndMakeReport();
    }

    @Override
    public Page<ProductCatalogEntity> findAll(String field, Boolean desc, Integer page) {
        Sort sorting = Sort.by("name");

        if (Objects.nonNull(field)) {
            switch (field) {
                case "brand" -> sorting = Sort.by("brand");
                case "price" -> sorting = Sort.by("price");
                case "launchingDate" -> sorting = Sort.by("launchingDate");
                case "rating" -> sorting = Sort.by("rating");

                default -> throw new IllegalStateException("Unexpected value: " + field);
            }
        }

        return (desc) ?
                this.productCatalogRepository.findAll(PageRequest.of(page, PAGE_SIZE, sorting.descending()))
                :
                this.productCatalogRepository.findAll(PageRequest.of(page, PAGE_SIZE, sorting.ascending()));
    }

    @Override
    public Page<ProductCatalogEntity> findAllByBrand(String brand, Integer page) {
        return productCatalogRepository.findAllByBrand(brand, PageRequest.of(page,PAGE_MIN_SIZE));
    }

    @Override
    public Integer countByBrand(String brand) {
        return productCatalogRepository.countTotalProductsByBrandStoreProcedure(brand);
    }
}
