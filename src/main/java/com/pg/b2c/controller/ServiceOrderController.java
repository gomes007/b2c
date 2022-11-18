package com.pg.b2c.controller;

import com.pg.b2c.domain.ServiceOrder;
import com.pg.b2c.service.ServiceOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-order")
public class ServiceOrderController {

    private ServiceOrderService service;

    public ServiceOrderController(ServiceOrderService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOrder create(@RequestBody ServiceOrder serviceOrder){
        return service.open(serviceOrder);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceOrder> findAll(){
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceOrder findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceOrder update(@PathVariable("id") Long id, @RequestBody ServiceOrder serviceOrder){
        this.service.exists(id);
        serviceOrder.setId(id);
        return this.service.open(serviceOrder);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceOrder delete(@PathVariable("id") Long id){
        return this.service.deleteById(id);
    }


}
