package com.pg.b2c.controller;

import com.pg.b2c.domain.AccountPlan;
import com.pg.b2c.service.AccountPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountPlan")
public class AccountPlanController {

    private AccountPlanService service;

    public AccountPlanController(AccountPlanService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountPlan create(@RequestBody AccountPlan accountPlan){
        return service.save(accountPlan);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountPlan> findAll(){
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountPlan findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountPlan update(@PathVariable("id") Long id, @RequestBody AccountPlan accountPlan){
        this.service.exists(id);
        accountPlan.setId(id);
        return this.service.save(accountPlan);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountPlan delete(@PathVariable("id") Long id){
        return this.service.deleteById(id);
    }


}
