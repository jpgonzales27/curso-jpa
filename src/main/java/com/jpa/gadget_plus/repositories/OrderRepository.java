package com.jpa.gadget_plus.repositories;

import com.jpa.gadget_plus.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
