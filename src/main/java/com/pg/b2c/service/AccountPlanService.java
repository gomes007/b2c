package com.pg.b2c.service;

import com.pg.b2c.domain.AccountPlan;
import com.pg.b2c.exceptions.impl.BadRequestException;
import com.pg.b2c.repository.AccountPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountPlanService {

    private AccountPlanRepository repository;

    public AccountPlanService(AccountPlanRepository repository) {
        this.repository = repository;
    }


    public AccountPlan save(AccountPlan accountPlan){
        return repository.save(accountPlan);
    }

    public List<AccountPlan> findAll(){
        return repository.findAll();
    }

    public AccountPlan findById(Long id){
        return this.repository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("nof found"));
    }

    public AccountPlan deleteById(Long id){
        AccountPlan accountPlan = this.findById(id);
        this.repository.delete(accountPlan);
        return accountPlan;
    }


    public void exists(Long id){
        this.findById(id);
    }





}
