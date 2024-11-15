package com.jpa.gadget_plus.controllers;

import com.jpa.gadget_plus.entities.ProductCatalogEntity;
import com.jpa.gadget_plus.service.ProductCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/product-catalog")
@RequiredArgsConstructor
public class ProductCatalogController {

    private final ProductCatalogService productCatalogService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductCatalogEntity> getById(@PathVariable String id){
        return ResponseEntity.ok(productCatalogService.findById(UUID.fromString(id)));
    }
}
