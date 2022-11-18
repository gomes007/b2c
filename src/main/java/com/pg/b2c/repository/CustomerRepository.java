package com.pg.b2c.repository;

import com.pg.b2c.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Page<Customer> findAllByNameIgnoreCaseContaining(
            String name,
            Pageable pageable
    );

}
