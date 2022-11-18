package com.pg.b2c.service;

import com.pg.b2c.domain.Payment;
import com.pg.b2c.exceptions.impl.BadRequestException;
import com.pg.b2c.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }


    public Payment save(Payment payment){
        return repository.save(payment);
    }

    public List<Payment> findAll(){
        return repository.findAll();
    }

    public Payment findById(Long id){
        return this.repository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("nof found"));
    }

    public Payment deleteById(Long id){
        Payment payment = this.findById(id);
        this.repository.delete(payment);
        return payment;
    }


    public void exists(Long id){
        this.findById(id);
    }





}
