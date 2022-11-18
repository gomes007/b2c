package com.pg.b2c.repository;

import com.pg.b2c.domain.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> {
}
