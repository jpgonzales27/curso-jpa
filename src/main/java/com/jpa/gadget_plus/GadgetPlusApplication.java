package com.jpa.gadget_plus;

import com.jpa.gadget_plus.entities.BillEntity;
import com.jpa.gadget_plus.entities.OrderEntity;
import com.jpa.gadget_plus.entities.ProductEntity;
import com.jpa.gadget_plus.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class GadgetPlusApplication implements CommandLineRunner {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCatalogRepository productCatalogRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public static void main(String[] args) {
        SpringApplication.run(GadgetPlusApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final var HOME = this.categoryRepository.findById(1L).orElseThrow();
        final var OFFICE = this.categoryRepository.findById(2L).orElseThrow();

        this.productCatalogRepository.findAll().forEach(product -> {
            if(product.getDescription().contains("home")){
                product.addCategory(HOME);
            }

            if(product.getDescription().contains("office")){
                product.addCategory(OFFICE);
            }

            this.productCatalogRepository.save(product);
        });
    }
}
