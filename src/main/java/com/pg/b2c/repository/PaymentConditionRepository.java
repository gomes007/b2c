package com.pg.b2c.repository;

import com.pg.b2c.domain.PaymentCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentConditionRepository extends JpaRepository<PaymentCondition, Long> {
}
