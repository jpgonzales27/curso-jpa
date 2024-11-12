package com.jpa.gadget_plus.repositories;

import com.jpa.gadget_plus.entities.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<BillEntity,Long> {
}
