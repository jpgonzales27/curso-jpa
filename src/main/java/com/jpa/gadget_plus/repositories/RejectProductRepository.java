package com.jpa.gadget_plus.repositories;

import com.jpa.gadget_plus.entities.RejectProductEntity;
import com.jpa.gadget_plus.entities.RejectProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RejectProductRepository extends JpaRepository<RejectProductEntity, RejectProductId> {
}
