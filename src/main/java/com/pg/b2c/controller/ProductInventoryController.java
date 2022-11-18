package com.pg.b2c.controller;

import com.pg.b2c.domain.ProductInventory;
import com.pg.b2c.service.ProductInventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product_inventory")
public class ProductInventoryController {

    private ProductInventoryService service;

    public ProductInventoryController(ProductInventoryService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductInventory create(@RequestBody ProductInventory productInventory){
        return this.service.save(productInventory);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductInventory> findAll(){
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductInventory findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductInventory update(@PathVariable("id") Long id, @RequestBody ProductInventory productInventory){
        this.service.inventoryExists(id);
        productInventory.setId(id);
        return this.service.save(productInventory);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductInventory delete(@PathVariable("id") Long id){
        return this.service.deleteById(id);
    }


}
