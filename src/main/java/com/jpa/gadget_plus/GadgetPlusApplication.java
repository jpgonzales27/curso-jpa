package com.jpa.gadget_plus;

import com.jpa.gadget_plus.entities.BillEntity;
import com.jpa.gadget_plus.entities.OrderEntity;
import com.jpa.gadget_plus.repositories.BillRepository;
import com.jpa.gadget_plus.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
//        orderRepository.findAll().forEach(System.out::println);
//        billRepository.findAll().forEach(System.out::println);
//
//        var bill = BillEntity.builder()
//                .rfc("AD21AD2341")
//                .totalAmount(BigDecimal.TEN)
//                .id("b-17")
//                .build();
//
//        var order = OrderEntity.builder()
//                .createdAt(LocalDateTime.now())
//                .clientName("Juan")
//                .bill(bill)
//                .build();
//
//        this.orderRepository.save(order);
//        var order = this.orderRepository.findById(17L).get();
//
//        System.out.println("----- Pre Persist -----");
//        System.out.println("Name: " + order.getClientName());
//        System.out.println("RFC: " + order.getBill().getRfc());
//
//        order.setClientName("Juan Gonzales Alvarado");
//        order.getBill().setRfc("aaaa1111");
//        this.orderRepository.save(order);
//
//        var order2 = this.orderRepository.findById(17L).get();
//
//        System.out.println("----- Post Persist -----");
//        System.out.println("Name: " + order2.getClientName());
//        System.out.println("RFC: " + order2.getBill().getRfc());

        var order = this.orderRepository.findById(17L).get();
        orderRepository.delete(order);
    }
}
