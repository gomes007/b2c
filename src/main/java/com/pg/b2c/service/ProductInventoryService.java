package com.pg.b2c.service;

import com.pg.b2c.domain.ProductInventory;
import com.pg.b2c.exceptions.impl.BadRequestException;
import com.pg.b2c.repository.ProductInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInventoryService {

    private ProductInventoryRepository repository;

    public ProductInventoryService(ProductInventoryRepository repository) {
        this.repository = repository;
    }


    public ProductInventory save(ProductInventory productInventory){
        return this.repository.save(productInventory);
    }


    public void subtractAmount(Long id, int amount){
        var found = this.findById(id);
        found.setCurrentlyQuantity(found.getCurrentlyQuantity() - amount);
        this.repository.save(found);
    }


    public void addAmount(Long id, int amount){
        var found = this.findById(id);
        found.setCurrentlyQuantity(found.getCurrentlyQuantity() + amount);
        this.repository.save(found);
    }


    public List<ProductInventory> findAll(){
        return repository.findAll();
    }

    public ProductInventory findById(Long id){
        return this.repository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("nof found"));
    }

    public ProductInventory existsCluster(Long id){
        return this.findById(id);
    }

    public ProductInventory deleteById(Long id){
        ProductInventory productInventory = this.findById(id);
        this.repository.delete(productInventory);
        return productInventory;
    }

    public void inventoryExists(Long id){
        this.findById(id);
    }



}
