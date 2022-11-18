package com.pg.b2c.repository;

import com.pg.b2c.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findAllByNameIgnoreCaseContaining(
            String name,
            Pageable pageable
    );

}
