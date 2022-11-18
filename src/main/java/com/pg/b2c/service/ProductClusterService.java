package com.pg.b2c.service;

import com.pg.b2c.domain.ProductCluster;
import com.pg.b2c.exceptions.impl.BadRequestException;
import com.pg.b2c.repository.ProductClusterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductClusterService {

    private ProductClusterRepository repository;

    public ProductClusterService(ProductClusterRepository repository) {
        this.repository = repository;
    }


    public ProductCluster save(ProductCluster productCluster){
        return this.repository.save(productCluster);
    }

    public List<ProductCluster> findAll(){
        return repository.findAll();
    }

    public ProductCluster findById(Long id){
        return this.repository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("nof found"));
    }

    public ProductCluster existsCluster(Long id){
        return this.findById(id);
    }

    public ProductCluster deleteById(Long id){
        ProductCluster productCluster = this.findById(id);
        this.repository.delete(productCluster);
        return productCluster;
    }


}
