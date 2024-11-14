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
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

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

    @Autowired
    private RejectProductRepository rejectProductRepository;


    public static void main(String[] args) {
        SpringApplication.run(GadgetPlusApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        var random = new Random();
//        var productsCatalog = new LinkedList<>(this.productCatalogRepository.findAll());
//
//        IntStream.range(0, productsCatalog.size()).forEach(i -> {
//            var idOrderRandom = random.nextLong(16) + 1;
//            var orderRandom = this.orderRepository.findById(idOrderRandom).orElseThrow();
//
//            var product = ProductEntity.builder()
//                    .quantity(BigInteger.valueOf(random.nextInt(5) + 1))
//                    .catalog(productsCatalog.poll())
//                    .build();
//
//            orderRandom.addProduct(product);
//            product.setOrder(orderRandom);
//
//            this.orderRepository.save(orderRandom);
//        });
        this.rejectProductRepository.findAll().forEach(System.out::println);
    }
}
