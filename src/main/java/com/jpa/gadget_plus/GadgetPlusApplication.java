package com.jpa.gadget_plus;

import com.jpa.gadget_plus.entities.BillEntity;
import com.jpa.gadget_plus.entities.OrderEntity;
import com.jpa.gadget_plus.entities.ProductEntity;
import com.jpa.gadget_plus.repositories.BillRepository;
import com.jpa.gadget_plus.repositories.OrderRepository;
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


    public static void main(String[] args) {
        SpringApplication.run(GadgetPlusApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var product1 = ProductEntity.builder().quantity(BigInteger.ONE).build();
        var product2 = ProductEntity.builder().quantity(BigInteger.TWO).build();
        var products = List.of(product1, product2);

        var order = this.orderRepository.findById(1L).orElseThrow();
        order.setProducts(products);

        products.forEach(product -> product.setOrder(order));

        this.orderRepository.save(order);
    }
}
