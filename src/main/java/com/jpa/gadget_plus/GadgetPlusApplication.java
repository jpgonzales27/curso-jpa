package com.jpa.gadget_plus;

import com.jpa.gadget_plus.entities.BillEntity;
import com.jpa.gadget_plus.entities.OrderEntity;
import com.jpa.gadget_plus.entities.ProductEntity;
import com.jpa.gadget_plus.repositories.BillRepository;
import com.jpa.gadget_plus.repositories.OrderRepository;
import com.jpa.gadget_plus.repositories.ProductCatalogRepository;
import com.jpa.gadget_plus.repositories.ProductRepository;
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


    public static void main(String[] args) {
        SpringApplication.run(GadgetPlusApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var productCatalog1 = this.productCatalogRepository.findAll().get(0);
        var productCatalog2 = this.productCatalogRepository.findAll().get(5);
        var productCatalog3 = this.productCatalogRepository.findAll().get(7);

        var order = this.orderRepository.findById(1L).get();

        var product1 = ProductEntity.builder().quantity(BigInteger.ONE).build();
        var product2 = ProductEntity.builder().quantity(BigInteger.TEN).build();
        var product3 = ProductEntity.builder().quantity(BigInteger.TWO).build();

        var productsList = List.of(product1, product2, product3);

        product1.setCatalog(productCatalog1);
        product2.setCatalog(productCatalog2);
        product3.setCatalog(productCatalog3);

        order.setProducts(productsList);
        productsList.forEach(product-> product.setOrder(order));

        this.orderRepository.save(order);
    }
}
