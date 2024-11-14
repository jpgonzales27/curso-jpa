package com.jpa.gadget_plus.repositories;

import com.jpa.gadget_plus.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
}
