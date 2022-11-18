package com.pg.b2c.controller;

import com.pg.b2c.domain.Payment;
import com.pg.b2c.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Payment create(@RequestBody Payment payment){
        return service.save(payment);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Payment> findAll(){
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Payment findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Payment update(@PathVariable("id") Long id, @RequestBody Payment payment){
        this.service.exists(id);
        payment.setId(id);
        return this.service.save(payment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Payment delete(@PathVariable("id") Long id){
        return this.service.deleteById(id);
    }


}
