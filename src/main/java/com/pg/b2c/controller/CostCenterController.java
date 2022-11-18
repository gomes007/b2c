package com.pg.b2c.controller;

import com.pg.b2c.domain.CostCenter;
import com.pg.b2c.service.CostCenterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cost-center")
public class CostCenterController {

    private CostCenterService service;

    public CostCenterController(CostCenterService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CostCenter create(@RequestBody CostCenter costCenter){
        return service.save(costCenter);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CostCenter> findAll(){
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CostCenter findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CostCenter update(@PathVariable("id") Long id, @RequestBody CostCenter costCenter){
        this.service.exists(id);
        costCenter.setId(id);
        return this.service.save(costCenter);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CostCenter delete(@PathVariable("id") Long id){
        return this.service.deleteById(id);
    }


}
