package com.pg.b2c.controller;

import com.pg.b2c.domain.Service_s;
import com.pg.b2c.service.Service_sService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service_s")
public class Service_sController {

    private Service_sService service;

    public Service_sController(Service_sService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Service_s create(@RequestBody Service_s service_s){
        return service.save(service_s);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Service_s> findAll(){
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Service_s findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Service_s update(@PathVariable("id") Long id, @RequestBody Service_s service_s){
        this.service.exists(id);
        service_s.setId(id);
        return this.service.save(service_s);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Service_s delete(@PathVariable("id") Long id){
        return this.service.deleteById(id);
    }


}
