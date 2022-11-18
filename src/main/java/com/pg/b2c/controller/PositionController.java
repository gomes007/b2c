package com.pg.b2c.controller;

import com.pg.b2c.domain.PositionSalary;
import com.pg.b2c.service.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/positionSalary")
public class PositionController {

    private PositionService service;

    public PositionController(PositionService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PositionSalary create(@RequestBody PositionSalary positionSalary){
        return this.service.save(positionSalary);
    }




}
