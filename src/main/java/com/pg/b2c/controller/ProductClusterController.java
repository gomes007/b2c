package com.pg.b2c.controller;

import com.pg.b2c.domain.ProductCluster;
import com.pg.b2c.service.ProductClusterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cluster")
public class ProductClusterController {

    private ProductClusterService service;

    public ProductClusterController(ProductClusterService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductCluster register(@RequestBody ProductCluster productCluster){
        return this.service.save(productCluster);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductCluster> findAll(){
        return this.service.findAll();
    }



}
