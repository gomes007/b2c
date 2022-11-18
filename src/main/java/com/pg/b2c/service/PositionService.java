package com.pg.b2c.service;

import com.pg.b2c.domain.PositionSalary;
import com.pg.b2c.exceptions.impl.NotFoundException;
import com.pg.b2c.repository.PositionSalaryRepository;
import org.springframework.stereotype.Service;

@Service
public class PositionService {

    private PositionSalaryRepository repository;

    public PositionService(PositionSalaryRepository repository) {
        this.repository = repository;
    }


    public PositionSalary save(PositionSalary positionSalary){
        return this.repository.save(positionSalary);
    }



    public PositionSalary findById(final Long id) {
        return this.repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("not found"));
    }


    public void positionSalaryExists(Long id){
        this.findById(id);
    }


    public PositionSalary deleteById(Long id){
        PositionSalary found = this.findById(id);
        this.repository.delete(found);
        return found;
    }

}
