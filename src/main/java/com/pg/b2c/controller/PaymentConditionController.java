package com.pg.b2c.controller;

import com.pg.b2c.domain.PaymentCondition;
import com.pg.b2c.service.PaymentConditionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-condition")
public class PaymentConditionController {

    private PaymentConditionService service;

    public PaymentConditionController(PaymentConditionService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentCondition create(@RequestBody PaymentCondition paymentCondition){
        return service.save(paymentCondition);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentCondition> findAll(){
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentCondition findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentCondition update(@PathVariable("id") Long id, @RequestBody PaymentCondition paymentCondition){
        this.service.exists(id);
        paymentCondition.setId(id);
        return this.service.save(paymentCondition);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentCondition delete(@PathVariable("id") Long id){
        return this.service.deleteById(id);
    }


}
