package com.pg.b2c.service;

import com.pg.b2c.domain.Price;
import com.pg.b2c.domain.Service_s;
import com.pg.b2c.exceptions.impl.BadRequestException;
import com.pg.b2c.repository.Service_sRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class Service_sService {

    private Service_sRepository repository;

    public Service_sService(Service_sRepository repository) {
        this.repository = repository;
    }


    public Service_s save(Service_s service_s){
        this.calculateSalePrice(service_s);
        return repository.save(service_s);
    }

    private void calculateSalePrice(Service_s service_sValue) {
        Optional.ofNullable(service_sValue).ifPresent(service_s -> {

            BigDecimal costValue = Optional.of(service_s.getPrice())
                    .map(Price::getCostValue)
                    .orElse(BigDecimal.ZERO);

            BigDecimal additionalCosts = Optional.of(service_s.getPrice())
                    .map(Price::getAdditionalCosts)
                    .orElse(BigDecimal.ZERO);

            BigDecimal finalCosts = costValue.add(additionalCosts);

            service_s.getPrice().setFinalCosts(finalCosts);

            BigDecimal salePrice = Optional.of(service_s.getPrice().getMarginProfit())
                    .map(finalCosts::multiply)
                    .map(f -> f.divide(BigDecimal.valueOf(100.00), RoundingMode.CEILING))
                    .map(finalCosts::add)
                    .orElse(BigDecimal.ZERO);

            service_s.getPrice().setSalePrice(salePrice.setScale(2, RoundingMode.CEILING));

        });
    }

    public List<Service_s> findAll(){
        return repository.findAll();
    }

    public Service_s findById(Long id){
        return this.repository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("nof found"));
    }

    public Service_s deleteById(Long id){
        Service_s service_s = this.findById(id);
        this.repository.delete(service_s);
        return service_s;
    }


    public void exists(Long id){
        this.findById(id);
    }





}
