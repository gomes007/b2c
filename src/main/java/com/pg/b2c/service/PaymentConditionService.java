package com.pg.b2c.service;

import com.pg.b2c.domain.PaymentCondition;
import com.pg.b2c.exceptions.impl.BadRequestException;
import com.pg.b2c.repository.PaymentConditionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentConditionService {

    private PaymentConditionRepository repository;

    public PaymentConditionService(PaymentConditionRepository repository) {
        this.repository = repository;
    }


    public PaymentCondition save(PaymentCondition paymentCondition){
        return repository.save(paymentCondition);
    }

    public List<PaymentCondition> findAll(){
        return repository.findAll();
    }

    public PaymentCondition findById(Long id){
        return this.repository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("nof found"));
    }

    public PaymentCondition deleteById(Long id){
        PaymentCondition paymentCondition = this.findById(id);
        this.repository.delete(paymentCondition);
        return paymentCondition;
    }


    public void exists(Long id){
        this.findById(id);
    }





}
