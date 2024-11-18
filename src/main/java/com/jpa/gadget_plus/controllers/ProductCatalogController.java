package com.jpa.gadget_plus.controllers;

import com.jpa.gadget_plus.dtos.ReportProduct;
import com.jpa.gadget_plus.entities.ProductCatalogEntity;
import com.jpa.gadget_plus.enums.DateEval;
import com.jpa.gadget_plus.enums.LikeKey;
import com.jpa.gadget_plus.service.ProductCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product-catalog")
@RequiredArgsConstructor
public class ProductCatalogController {

    private final ProductCatalogService productCatalogService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductCatalogEntity> getById(@PathVariable String id) {
        return ResponseEntity.ok(productCatalogService.findById(UUID.fromString(id)));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductCatalogEntity> getByName(@PathVariable String name) {
        return ResponseEntity.ok(productCatalogService.findByName(name));
    }

    @GetMapping("/like/{key}")
    public ResponseEntity<List<ProductCatalogEntity>> getByName(@PathVariable LikeKey key, @RequestParam String word) {
        final var comodin = '%';
        if (key.equals(LikeKey.AFTER)) {
            return ResponseEntity.ok(productCatalogService.findNameLike(comodin + word)); //%word
        }

        if (key.equals(LikeKey.BEFORE)) {
            return ResponseEntity.ok(productCatalogService.findNameLike(word + comodin)); //%word
        }

        if (key.equals(LikeKey.BETWEEN)) {
            return ResponseEntity.ok(productCatalogService.findNameLike(comodin + word + comodin)); //%word
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/between")
    public ResponseEntity<List<ProductCatalogEntity>> getByPriceByBetween(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max
    ) {
        return ResponseEntity.ok(productCatalogService.findByPriceBetween(min, max));
    }

    @GetMapping("/between-query")
    public ResponseEntity<List<ProductCatalogEntity>> getByPriceBetweenQuery(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max
    ) {
        return ResponseEntity.ok(productCatalogService.findBetweenByQuery(min, max));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductCatalogEntity>> getByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(productCatalogService.findByCategoryId(id));
    }

    @GetMapping("/date-launch/{key}")
    public ResponseEntity<List<ProductCatalogEntity>> getByDate(
            @PathVariable DateEval key,
            @RequestParam LocalDate date) {
        return ResponseEntity.ok(this.productCatalogService.findByLaunchingDate(date, key));
    }

    @GetMapping("/brand-rating")
    public ResponseEntity<List<ProductCatalogEntity>> getByBrandAndRating(
            @RequestParam String brand,
            @RequestParam short rating) {
        return ResponseEntity.ok(this.productCatalogService.findByBrandAndRating(brand,rating));
    }

    @GetMapping("/brand-rating-or")
    public ResponseEntity<List<ProductCatalogEntity>> getByBrandOrRating(
            @RequestParam String brand,
            @RequestParam short rating) {
        return ResponseEntity.ok(this.productCatalogService.findByBrandOrRating(brand,rating));
    }

    @GetMapping("/report")
    public ResponseEntity<List<ReportProduct>> makeReport() {
        return ResponseEntity.ok(this.productCatalogService.makeReport());
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ProductCatalogEntity>> getAll(
            @RequestParam(required=false) String field,
            @RequestParam Boolean desc,
            @RequestParam Integer page) {
        return ResponseEntity.ok(this.productCatalogService.findAll(field,desc,page));
    }

    @GetMapping("/all-by-brand")
    public ResponseEntity<Page<ProductCatalogEntity>> getAllByBrand(
            @RequestParam String brand,
            @RequestParam Integer page) {
        return ResponseEntity.ok(this.productCatalogService.findAllByBrand(brand,page));
    }
}
