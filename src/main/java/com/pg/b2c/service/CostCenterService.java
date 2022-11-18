package com.pg.b2c.service;

import com.pg.b2c.domain.CostCenter;
import com.pg.b2c.domain.CostCenterStatus;
import com.pg.b2c.exceptions.impl.BadRequestException;
import com.pg.b2c.repository.CostCenterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostCenterService {

    private CostCenterRepository repository;

    public CostCenterService(CostCenterRepository repository) {
        this.repository = repository;
    }


    public CostCenter save(CostCenter costCenter){
        costCenter.setCostCenterStatus(CostCenterStatus.Enable);
        return repository.save(costCenter);
    }

    public List<CostCenter> findAll(){
        return repository.findAll();
    }

    public CostCenter findById(Long id){
        return this.repository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("nof found"));
    }

    public CostCenter deleteById(Long id){
        CostCenter costCenter = this.findById(id);
        this.repository.delete(costCenter);
        return costCenter;
    }


    public void exists(Long id){
        this.findById(id);
    }





}
